package frontend.listeners

import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan

/**
 * Логирует старт/финиш плана тестов и каждого теста.
 * Регистрация через `META-INF/services/org.junit.platform.launcher.TestExecutionListener`.
 */
class LoggingTestExecutionListener : TestExecutionListener {

    override fun testPlanExecutionStarted(testPlan: TestPlan) {
        val count = testPlan.countTestIdentifiers(TestIdentifier::isTest)
        println("[Listener] План тестов запущен, тестов в плане: $count")
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan) {
        println("[Listener] План тестов завершён")
    }

    override fun executionStarted(testIdentifier: TestIdentifier) {
        if (testIdentifier.isTest) {
            println("[Listener] Старт: ${testIdentifier.displayName}")
        }
    }

    override fun executionFinished(testIdentifier: TestIdentifier, testExecutionResult: TestExecutionResult) {
        if (testIdentifier.isTest) {
            println("[Listener] Финиш: ${testIdentifier.displayName} -> ${testExecutionResult.status}")
        }
    }
}
