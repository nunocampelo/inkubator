package pt.base.inkubator.prism.algorithm

import kotlin.math.log10
import kotlin.math.pow

abstract class Algorithm<A : Comparable<A>, R>(private val minLongArgument: Long, private val maxLongArgument: Long) {
    abstract suspend fun exec(arg: A): R

    abstract fun produceArgument(): A

    override fun toString(): String {
        return this.javaClass.simpleName
    }

    protected fun defaultLongArgumentProducer(): Long {
//        return minLongArgument + ((maxLongArgument - minLongArgument) * Math.random()).toLong()
        val exponent = (log10((maxLongArgument / minLongArgument).toDouble()) * Math.random()).toFloat()
        return minLongArgument * 10F.pow(exponent).toLong()
    }
}