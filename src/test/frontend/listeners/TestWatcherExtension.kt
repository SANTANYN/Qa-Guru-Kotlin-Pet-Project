package frontend.listeners

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher

/**
 * Демонстрация JUnit Jupiter [TestWatcher]: логирует успех / падение / пропуск / abort.
 * Подключение: `@ExtendWith(TestWatcherExtension::class)` на тестовом классе.
 */
class TestWatcherExtension : TestWatcher {

    override fun testSuccessful(context: ExtensionContext) {
        println("|--- Test passed: ${context.displayName} ---|")
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable?) {
        println("|--- Test failed: ${context.displayName} ---|")
        println("Cause: ${cause?.message}")
    }

    override fun testAborted(context: ExtensionContext, cause: Throwable?) {
        println("|--- Test aborted: ${context.displayName} ---|")
        println("Cause: ${cause?.message}")
    }

    override fun testDisabled(context: ExtensionContext, reason: java.util.Optional<String>) {
        println("|--- Test disabled: ${context.displayName} ---|")
        println("Reason: ${reason.orElse("none")}")
    }
}
