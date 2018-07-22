package pt.base.inkubator.prism.algorithm.runner

import pt.base.inkubator.prism.algorithm.Algorithm

abstract class AlgorithmRunner<A : Comparable<A>, AR, R>(algorithm: Algorithm<A, AR>, argument: A) {
    abstract suspend fun run(): R
}