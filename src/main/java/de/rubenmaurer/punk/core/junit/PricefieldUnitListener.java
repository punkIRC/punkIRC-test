package de.rubenmaurer.punk.core.junit;

import de.rubenmaurer.punk.util.Terminal;
import org.fusesource.jansi.Ansi;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.fusesource.jansi.Ansi.ansi;

public class PricefieldUnitListener implements TestExecutionListener {

    private String display;

    public static Map<TestExecutionResult.Status, List<String>> testResults = new HashMap<>();

    private int testCount, success, failed, aborted;

    private int maxTestCount, maxSuccess, maxFailed, maxAborted;

    public void executionStarted(TestIdentifier testIdentifier) {
        display = testIdentifier.getDisplayName();
        if(!display.contains("JUnit")) {

            String status = ansi().fgBlue().render("PROCESSING").fgDefault().toString();
            if (!testIdentifier.isContainer()) {
                System.out.println(ansi().saveCursorPosition()
                        .render(Terminal.twoSidedColumn(display, String.valueOf(ansi().render(Terminal.cageStatus(status))))));

                testCount++;
                maxTestCount++;
                return;
            }

            System.out.print(ansi()
                    .render("\r\n")
                    .render(Terminal.getDivider("="))
                    .render(Terminal.center(String.format("=== %s ===", display.toUpperCase())))
                    .render(Terminal.getDivider("-")));

            testCount = success = failed = aborted = 0;
        }
    }

    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (!testIdentifier.getDisplayName().contains("JUnit")) {
            if (!testIdentifier.isContainer()) {
                TestExecutionResult.Status status = testExecutionResult.getStatus();

                String result = "";
                if (status.equals(TestExecutionResult.Status.FAILED) || status.equals(TestExecutionResult.Status.ABORTED)) {
                    result = ansi().fgRed().render(status.name()).fgDefault().toString();
                    if (status.equals(TestExecutionResult.Status.ABORTED)) {
                        aborted++;
                        maxAborted++;
                    }

                    if (status.equals(TestExecutionResult.Status.FAILED)) {
                        failed++;
                        maxFailed++;
                    }
                }

                if (status.equals(TestExecutionResult.Status.SUCCESSFUL)) {
                    result = ansi().fgGreen().render(status.name()).fgDefault().toString();
                    success++;
                    maxSuccess++;
                }

                System.out.println(ansi().restoreCursorPosition().eraseLine(Ansi.Erase.ALL)
                        .render(Terminal.twoSidedColumn(display, String.valueOf(ansi().render(Terminal.cageStatus(result))))));

                if (testResults.getOrDefault(status, null) == null) {
                    testResults.put(status, new LinkedList<>());
                }

                testResults.get(status).add(testIdentifier.getDisplayName());

                return;
            }

            String summary = String.format("%s%s",
                    Terminal.center(String.format("[TESTS]: %s [SUCCESS]: %s [ABORTED]: %s [FAILURES]: %s", testCount, success, aborted, failed)),
                    Terminal.center(String.format("[SUCCESS-RATE]: %d%%", (int)((success * 1.0 / testCount * 1.0) * 100))));

            System.out.println(ansi()
                    .render(Terminal.getDivider("-"))
                    .render(summary)
                    .render(Terminal.getDivider("=")));
        }
    }
}
