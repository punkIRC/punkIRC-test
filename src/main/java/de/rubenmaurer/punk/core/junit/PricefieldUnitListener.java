package de.rubenmaurer.punk.core.junit;

import de.rubenmaurer.punk.core.util.Terminal;
import org.fusesource.jansi.Ansi;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import static org.fusesource.jansi.Ansi.ansi;

public class PricefieldUnitListener implements TestExecutionListener {

    private String display;

    public void executionStarted(TestIdentifier testIdentifier) {
        display = testIdentifier.getDisplayName();

        String status = ansi().fgBlue().render("PROCEESING").fgDefault().toString();
        if(!display.contains("JUnit")) {
            if (!testIdentifier.isContainer()) {
                System.out.println(ansi().saveCursorPosition()
                        .render(Terminal.twoSidedColumn(display, String.valueOf(ansi().render(Terminal.cageStatus(status))))));

                return;
            }

            System.out.print(ansi()
                    .render(Terminal.getDivider("-"))
                    .render(Terminal.center(display))
                    .render(Terminal.getDivider("-")));
        }
    }

    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
        String result = ansi().fgYellow().render("IGNORE").fgDefault().toString();

        System.out.println(ansi().restoreCursorPosition().eraseLine(Ansi.Erase.ALL)
                .render(Terminal.twoSidedColumn(display, String.valueOf(ansi().render(Terminal.cageStatus(result))))));
    }

    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        TestExecutionResult.Status status = testExecutionResult.getStatus();

        String result = ansi().fgGreen().render(status.name()).fgDefault().toString();
        if (status.equals(TestExecutionResult.Status.FAILED) || status.equals(TestExecutionResult.Status.ABORTED)) {
            result = ansi().fgRed().render(status.name()).fgDefault().toString();
        }

        System.out.println(ansi().restoreCursorPosition().eraseLine(Ansi.Erase.ALL)
                .render(Terminal.twoSidedColumn(display, String.valueOf(ansi().render(Terminal.cageStatus(result))))));
    }
}
