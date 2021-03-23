package seedu.duke.commands;

import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import static seedu.duke.common.Messages.MESSAGE_ADDED_TASK;

public class AddTaskCommand extends Command {

    private final Task task;

    //@@author aliciatay-zls
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds new task to selected module.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        Module module = ModuleList.getSelectedModule();
        boolean isAddTaskAllowed = module.getIsAddTaskAllowed(ui, task);
        if (!isAddTaskAllowed) {
            return;
        }
        boolean isGraded = task.getIsTaskGraded(ui);
        task.setGraded(isGraded);
        module.addTask(task);
        ui.printMessage(String.format(MESSAGE_ADDED_TASK, task.getDescription()));
        ModuleList.writeModule();
        ModuleList.sortTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
