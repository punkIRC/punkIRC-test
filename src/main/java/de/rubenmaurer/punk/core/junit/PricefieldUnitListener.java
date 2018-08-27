package de.rubenmaurer.punk.core.junit;

import de.rubenmaurer.punk.core.util.Terminal;
import org.fusesource.jansi.Ansi;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import static org.fusesource.jansi.Ansi.ansi;

public class PricefieldUnitListener implements TestExecutionListener {

    private String display;

    private int testCount, success, failed, aborted;

    public void executionStarted(TestIdentifier testIdentifier) {
        display = testIdentifier.getDisplayName();

        String status = ansi().fgBlue().render("PROCESSING").fgDefault().toString();
        if(!display.contains("JUnit")) {
            if (!testIdentifier.isContainer()) {
                System.out.println(ansi().saveCursorPosition()
                        .render(Terminal.twoSidedColumn(display, String.valueOf(ansi().render(Terminal.cageStatus(status))))));

                testCount++;
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

    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
        String result = ansi().fgYellow().render("IGNORE").fgDefault().toString();

        System.out.println(ansi().restoreCursorPosition().eraseLine(Ansi.Erase.ALL)
                .render(Terminal.twoSidedColumn(display, String.valueOf(ansi().render(Terminal.cageStatus(result))))));
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
                    }

                    if (status.equals(TestExecutionResult.Status.FAILED)) {
                        failed++;
                    }
                }

                if (status.equals(TestExecutionResult.Status.SUCCESSFUL)) {
                    result = ansi().fgGreen().render(status.name()).fgDefault().toString();
                    success++;
                }

                System.out.println(ansi().restoreCursorPosition().eraseLine(Ansi.Erase.ALL)
                        .render(Terminal.twoSidedColumn(display, String.valueOf(ansi().render(Terminal.cageStatus(result))))));

                return;
            }

            System.out.println(ansi()
                    .render(Terminal.getDivider("-"))
                    .render(Terminal.center(String.format("[TESTS]: %s [SUCCESS]: %s [ABORTED]: %s [FAILURES]: %s", testCount, success, aborted, failed)))
                    .render(Terminal.center(String.format("[SUCCESS-RATE]: %d%%", (int)((success * 1.0 / testCount * 1.0) * 100))))
                    .render(Terminal.getDivider("=")));
        }
    }
}
