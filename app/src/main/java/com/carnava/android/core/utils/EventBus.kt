package com.carnava.android.core.utils

import timber.log.Timber

class EventBus {

    private val events = hashMapOf<Pair<Int, Events>, (result: Any) -> Unit>()

    /**
     * Подписываестя на событие
     * */
    fun <T : Any> setEventListener(
        idObserver: Any,
        idAction: Events,
        listener: (result: T) -> Unit
    ) {
        events[idObserver.hashCode() to idAction] = listener as (data: Any) -> Unit
        Timber.d("Set event listener for entity $idObserver with actionId $idAction")
    }

    /**
     * Удаляет все подписки на все события у idObserver
     * */
    fun removeEventListeners(idObserver: Any) {
        val idObserverHashCode = idObserver.hashCode()
        val keys = events.keys.toList()
        keys.forEach {
            if (idObserverHashCode == it.first) {
                events.remove(it)
            }
        }
        Timber.d("Removed all event listeners for entity $idObserver")
    }

    /**
     * Удаляет все подписки на конкретное событие у idObserver
     * */
    fun removeEventListeners(idObserver: Any, idEvent: Events) {
        val keys = events.keys.toList()
        keys.forEach {
            if (idObserver == it.first && idEvent == it.second) {
                events.remove(it)
            }
        }
        Timber.d("Removed all event listeners for entity $idObserver")
    }

    /**
     * Отправляет событие всем кто подписался на него
     * */
    fun sendEvent(idEvent: Events, data: Any) {
        events.forEach { (key, value) ->
            if (key.second == idEvent) {
                value.invoke(data)
            }
        }
        Timber.d("Sent result to entity with actionId $idEvent ")
    }

    /**
     * Отправляет событие конкретному idObserver, если он подписан на это событие
     * */
    fun sendEvent(idObserver: Any, idEvent: Events, result: Any) {
        events.forEach { (key, value) ->
            if (key.first == idObserver &&
                key.second == idEvent
            ) {
                value.invoke(result)
            }
        }
        Timber.d("Sent result to entity $idObserver with actionId $idEvent")
    }

    enum class Events {
        ADD_FAVORITE, REMOVE_FAVORITE,
        ADD_TO_CART, REMOVE_FROM_CART, CLEAR_CART
    }
}
