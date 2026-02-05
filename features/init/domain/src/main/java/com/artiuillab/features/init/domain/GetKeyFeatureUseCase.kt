package com.artiuillab.features.init.domain

import com.elveum.container.Container
import com.elveum.container.subject.ContainerConfiguration
import com.elveum.container.subject.LazyFlowSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetKeyFeatureUseCase @Inject constructor() {
    fun invoke(): Flow<Container<KeyFeature>> {
        // todo: load real data
        return LazyFlowSubject.create {
            delay(2000)
            emit(
                KeyFeature(
                    0L,
                    "Est ut quam qui suscipit quod.",
                    "Iste pariatur mollitia fugit. Ipsam doloremque quae et officiis aut est delectus. Voluptatem dolorem similique pariatur vitae suscipit voluptatem aspernatur."
                )
            )
        }.listen(ContainerConfiguration(emitReloadFunction = true))
    }
}