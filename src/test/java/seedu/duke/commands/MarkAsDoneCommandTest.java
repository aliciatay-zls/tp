package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.commands.CommandTestUtil.NEWLINE;
import static seedu.duke.commands.CommandTestUtil.initialiseModule;
import static seedu.duke.commands.CommandTestUtil.initialiseTaskList;

class MarkAsDoneCommandTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    private final ModuleList modules = new ModuleList();

    @Test
    void execute_markTwoCommand_markedTwoSuccessfully() {
        String input = "1 2" + NEWLINE;
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
        System.setOut(new PrintStream(bos));
        UI ui = new UI();

        Module module = initialiseModule(modules);

        ArrayList<Task> taskList = initialiseTaskList(module);

        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand();
        markAsDoneCommand.execute(modules, ui);

        String output = "Which undone tasks have you completed?" + NEWLINE
                + "1. weekly exercise" + NEWLINE
                + "2. lecture quiz" + NEWLINE
                + "3. read up notes" + NEWLINE + NEWLINE
                + "Please enter the indices of the tasks you would like to mark as done." + NEWLINE
                + "Separate indices with a blank space." + NEWLINE + NEWLINE
                + "Marked weekly exercise as done." + NEWLINE
                + "Marked lecture quiz as done." + NEWLINE;

        // checks displayed output to user
        assertEquals(output, bos.toString());

        String actualDone = "";
        for (Task task : taskList) {
            actualDone += task.getDone() + NEWLINE;
        }
        String expectedDone = "true" + NEWLINE
                + "true" + NEWLINE
                + "true" + NEWLINE
                + "false" + NEWLINE
                + "true" + NEWLINE
                + "true" + NEWLINE;

        // checks if tasks were correctly marked in task list
        assertEquals(expectedDone, actualDone);

        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}