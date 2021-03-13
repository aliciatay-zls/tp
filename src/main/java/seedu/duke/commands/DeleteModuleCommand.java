package seedu.duke.commands;

import seedu.duke.common.Messages;
import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.Messages.MESSAGE_DELETE_MODULE_INFO;
import static seedu.duke.common.Messages.MESSAGE_MODULE_TO_DELETE;
import static seedu.duke.common.Messages.MESSAGE_REMOVED_MODULE;
import static seedu.duke.common.Messages.NEWLINE;

public class DeleteModuleCommand extends Command {

    //@@author isaharon
    /**
     * Requests for list of indices to delete.
     * Deletes all modules corresponding to specified indices.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ui.printMessage(getDeleteInfo());

        // TODO validate list of integers. Assume input is valid for now.
        ArrayList<Integer> indices = ui.readIntegers();
        ArrayList<String> deletedModulesCodes = ModuleList.deleteModules(indices);
        ui.printMessage(getDeletedModuleCodes(deletedModulesCodes));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    //@@author isaharon
    /**
     * Returns string containing instructions and module list.
     *
     * @return Message to print.
     */
    private String getDeleteInfo() {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_MODULE_TO_DELETE);
        ArrayList<String> modules = ModuleList.getModules();
        for (String moduleCode : modules) {
            int counter = modules.indexOf(moduleCode) + 1;
            stringBuilder.append(String.format(Messages.FORMAT_LIST_ITEMS, counter, moduleCode));
            stringBuilder.append(NEWLINE);
        }
        stringBuilder.append(NEWLINE).append(MESSAGE_DELETE_MODULE_INFO);
        return stringBuilder.toString();
    }

    //@@author isaharon
    /**
     * Returns string containing list of deleted modules.
     *
     * @param deletedModuleCodes Arraylist of module codes.
     * @return Message to print.
     */
    private String getDeletedModuleCodes(ArrayList<String> deletedModuleCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String moduleCode : deletedModuleCodes) {
            stringBuilder.append(String.format(MESSAGE_REMOVED_MODULE, moduleCode));
            stringBuilder.append(NEWLINE);
        }
        return stringBuilder.toString();
    }
}
