package pt.base.inkubator.prism.algorithm.runner

import pt.base.inkubator.prism.algorithm.Algorithm
import javax.management.MBeanServerConnection
import javax.management.ObjectName

class JMXTimedAlgorithmRunner<A, R>(
    private val jmxServerConnection: MBeanServerConnection, private val algorithm: Algorithm<A, R>, private
    val argument: A
) :
    CpuTimedAlgorithmRunner<A, R>
        (
        algorithm,
        argument
    ) {

    private val processCpuTimePropName = "ProcessCpuTime"
    private val operatingSystemObjectName = "java.lang:type=OperatingSystem"

    override fun getCurrentCpuTime(): Long {
        return jmxServerConnection.getAttribute(
            ObjectName(operatingSystemObjectName),
            processCpuTimePropName
        ) as Long
    }
}