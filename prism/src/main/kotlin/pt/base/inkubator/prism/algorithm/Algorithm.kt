package pt.base.inkubator.prism.algorithm

abstract class Algorithm<A, R> {
    abstract suspend fun exec(arg: A): R

    private val maxLongArgument = 1000000L
    private val minLongArgument = 10L

    abstract fun produceArgument(): A

    override fun toString(): String {
        return this.javaClass.simpleName
    }

    protected fun defaultLongArgumentProducer(): Long {
        return (minLongArgument + (maxLongArgument - minLongArgument) * Math.random()).toLong()
    }
}