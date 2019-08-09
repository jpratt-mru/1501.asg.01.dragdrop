public class BrowserAutomationAsg {

    private static final String PROMPT = "> ";

    private Browzbot browzbot;

    private final UserInterface ui;

    public BrowserAutomationAsg(UserInterface ui) {
        this.ui = ui;
    }

    public void run() {

        displayWelcome();

        selectBrowser();

        interactWithUserUntilTheyQuit();

        displayThanks();

    }

    private void displayWelcome() {
        ui.println("Welcome to my first assignment.");

    }

    private void selectBrowser() {
        String browserName = ui.stringFromUser("browser? ");
        this.browzbot = new Browzbot(browserName);
    }

    private void interactWithUserUntilTheyQuit() {

        String userResponse = ui.stringFromUser(PROMPT);

        while (userWishesToContinue(userResponse)) {
            Instruction instruction = new Instruction(userResponse);

            Result result = browzbot.run(instruction);
            displayResultOfInstruction(result);

            userResponse = ui.stringFromUser(PROMPT);
        }

    }

    private void displayResultOfInstruction(Result result) {
        ui.println(result.msg());

    }

    private boolean userWishesToContinue(String userResponse) {
        return !userResponse.equalsIgnoreCase("STOP");
    }

    private void displayThanks() {
        ui.println("Ending....");
        browzbot.run(new Instruction("stop"));
        ui.println("Thanks!");

    }

}
