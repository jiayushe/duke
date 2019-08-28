import java.util.Scanner;

public class Duke {
    private static String[] welcomeMessage = {
        "Hello! I'm",
        " ____        _        ",
        "|  _ \\ _   _| | _____ ",
        "| | | | | | | |/ / _ \\",
        "| |_| | |_| |   <  __/",
        "|____/ \\__,_|_|\\_\\___|",
        "What can I do for you?"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Ui.print(welcomeMessage);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            String command = inputArr[0];
            try {
                switch (command) {
                case "bye":
                    Ui.print("Bye! Hope to see you again soon!");
                    return;
                case "list":
                    Task.printList();
                    break;
                case "done":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Task number missing!");
                    }
                    try {
                        Task.doTask(Integer.parseInt(inputArr[1]));
                    } catch (NumberFormatException e) {
                        throw new DukeException("☹ OOPS! Task number format invalid!");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS! Task number invalid!");
                    }
                    break;
                case "delete":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Task number missing!");
                    }
                    try {
                        Task.deleteTask(Integer.parseInt(inputArr[1]));
                    } catch (NumberFormatException e) {
                        throw new DukeException("☹ OOPS! Task number format invalid!");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS! Task number invalid!");
                    }
                    break;
                case "todo":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Todo description missing!");
                    }
                    Task.addNewTask(new ToDo(inputArr[1]));
                    break;
                case "deadline":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Deadline description missing!");
                    }
                    String[] deadlineInputArr = inputArr[1].split(" /by ");
                    if (deadlineInputArr.length <= 1) {
                        if (inputArr[1].indexOf("/by") == 0) {
                            throw new DukeException("☹ OOPS! Deadline description format invalid!");
                        } else {
                            throw new DukeException("☹ OOPS! Deadline due date missing!");
                        }
                    }
                    Task.addNewTask(new Deadline(deadlineInputArr[0], deadlineInputArr[1]));
                    break;
                case "event":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Event description missing!");
                    }
                    String[] eventInputArr = inputArr[1].split(" /at ");
                    if (eventInputArr.length <= 1) {
                        if (inputArr[1].indexOf("/at") == 0) {
                            throw new DukeException("☹ OOPS! Event description format invalid!");
                        } else {
                            throw new DukeException("☹ OOPS! Event timing missing!");
                        }
                    }
                    Task.addNewTask(new Event(eventInputArr[0], eventInputArr[1]));
                    break;
                default:
                    throw new DukeException("☹ OOPS! I can't do it!");
                }
            } catch (DukeException e) {
                Ui.print(e.getMessage());
            }
        }

        sc.close();
    }
}
