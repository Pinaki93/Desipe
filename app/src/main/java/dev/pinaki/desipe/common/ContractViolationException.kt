package dev.pinaki.desipe.common

/**
 * Exception to be thrown when a given contract between any two components
 * such as View and ViewModel, Repository and ViewModel etc are violated
 */
class ContractViolationException(message: String) : RuntimeException(message)