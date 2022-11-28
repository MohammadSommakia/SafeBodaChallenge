package com.safeboda.domain.usecases.base

/**
 * Contract for parameter classes
 */
interface UseCaseParameters

/**
 * Symbolizes absence of parameters for [BaseUseCase] & [BaseNetworkUseCase]
 */
class None : UseCaseParameters
