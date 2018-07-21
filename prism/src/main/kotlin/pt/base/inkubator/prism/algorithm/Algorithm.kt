package pt.base.inkubator.prism.algorithm

abstract class Algorithm<A, R>(private val minLongArgument: Long, private val maxLongArgument: Long) {
    abstract suspend fun exec(arg: A): R

    abstract fun produceArgument(): A

    override fun toString(): String {
        return this.javaClass.simpleName
    }

    protected fun defaultLongArgumentProducer(): Long {
        return (minLongArgument + (maxLongArgument - minLongArgument) * Math.random()).toLong()
    }
}