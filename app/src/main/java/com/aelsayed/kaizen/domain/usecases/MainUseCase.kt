package com.aelsayed.kaizen.domain.usecases

interface MainUseCase<in Parameter, out Result> {
     operator fun invoke(params: Parameter): Result
}