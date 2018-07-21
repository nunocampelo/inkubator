package pt.base.inkubator.prism.configuration.jmx

import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.*
import org.springframework.jmx.support.ConnectorServerFactoryBean
import org.springframework.remoting.rmi.RmiRegistryFactoryBean
import pt.base.inkubator.prism.algorithm.Algorithm
import pt.base.inkubator.prism.algorithm.runner.CpuTimedAlgorithmRunner
import pt.base.inkubator.prism.algorithm.runner.JMXTimedAlgorithmRunner
import javax.management.MBeanServerConnection
import javax.management.remote.JMXConnectorFactory
import javax.management.remote.JMXServiceURL

@Configuration
class JMXConfiguration(@Value("\${jmx.server.host:localhost}") private val jmxServerHost: String, @Value("\${jmx.server.port:8081}")
private val jmxServerPort: Int) {

    private val jmxServiceUrl: String
    private val rmiConnectorName = "connector:name=rmi"
    private val jmxServiceUrlPattern: String = "service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi"
    private lateinit var jmxServerConnection: MBeanServerConnection

    init {
        jmxServiceUrl = String.format(jmxServiceUrlPattern, jmxServerHost, jmxServerPort, jmxServerHost, jmxServerPort)
    }

    @Bean
    @Lazy
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    protected fun <A, R> wrapperFactory(algorithm: Algorithm<A, R>, argument: A): CpuTimedAlgorithmRunner<A, R> {
        return JMXTimedAlgorithmRunner<A, R>(jmxServerConnection, algorithm, argument)
    }

    @Bean
    fun rmiRegistry(): RmiRegistryFactoryBean {

        val rmiRegistryFactoryBean = RmiRegistryFactoryBean()

        rmiRegistryFactoryBean.port = jmxServerPort
        rmiRegistryFactoryBean.setAlwaysCreate(true)

        return rmiRegistryFactoryBean
    }

    @Bean
    @DependsOn("rmiRegistry")
    fun factoryBeanFactory(): ConnectorServerFactoryBean {

        var jmxFactoryBean = ConnectorServerFactoryBean()

        jmxFactoryBean.setObjectName(rmiConnectorName)
        jmxFactoryBean.setServiceUrl(jmxServiceUrl)

        return jmxFactoryBean
    }

    @Bean
    @DependsOn("factoryBeanFactory")
    fun jmxServerConnectionFactory(): MBeanServerConnection? {

        val url: JMXServiceURL
        try {

            url = JMXServiceURL(jmxServiceUrl)
            val jmxc = JMXConnectorFactory.connect(url, null)
            jmxc.connect()

            println("testme")
            jmxServerConnection = jmxc.mBeanServerConnection
        } catch (e: Exception) {

        }

        return jmxServerConnection
    }
}