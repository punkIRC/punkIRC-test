package de.rubenmaurer.punk.core.junit;

import de.rubenmaurer.punk.Pricefield;
import de.rubenmaurer.punk.core.util.Settings;
import de.rubenmaurer.punk.core.util.Terminal;
import org.fusesource.jansi.Ansi;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.fusesource.jansi.Ansi.ansi;

public class PricefieldUnitListener implements TestExecutionListener {

    private String display;

    private int testCount, success, failed, aborted;

    public void executionStarted(TestIdentifier testIdentifier) {
        display = testIdentifier.getDisplayName();
        if(!display.contains("JUnit")) {

            String status = ansi().fgBlue().render("PROCESSING").fgDefault().toString();
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

            PrintStream out = System.out;
            String summary = String.format("%s%s",
                    Terminal.center(String.format("[TESTS]: %s [SUCCESS]: %s [ABORTED]: %s [FAILURES]: %s", testCount, success, aborted, failed)),
                    Terminal.center(String.format("[SUCCESS-RATE]: %d%%", (int)((success * 1.0 / testCount * 1.0) * 100))));

            try {
                System.setOut(new PrintStream(new FileOutputStream(
                        new File(String.format("%s/%s/result.log", Settings.results(), Pricefield.ID)))));

                System.out.println(summary);
            } catch(Exception e) {
                Terminal.printError(Terminal.center(e.getMessage()));
            } finally {
                System.setOut(out);
            }

            System.out.println(ansi()
                    .render(Terminal.getDivider("-"))
                    .render(summary)
                    .render(Terminal.getDivider("=")));
        }
    }
}
