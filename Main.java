import java.util.Scanner;

public class Main {

    public static Scanner scan;
    public static int movesLeft = 30;
    public static String output = "You find yourself in a large foyer. There is a door to the north. To the west is a bench against the wall. To the east is a chest on the floor. In the center of the room is a large candle. The candle is unlit.";

    public static boolean inFoyer = true;
    public static boolean inLibrary = false;
    public static boolean inConservatory = false;

    //foyer conditions
    public static boolean chestOpen = false;
    public static boolean hasMatches = false;
    public static boolean candleLit = false;
    public static boolean door1Locked = true;
    public static boolean door1Open = false;

    //library conditions
    public static boolean getPen = false;
    public static boolean getBook = false;
    public static boolean writeBook = false;
    public static boolean door2Locked = true;
    public static boolean door2Open = false;

    //conservatory conditions
    public static boolean playTrumpet = false;
    public static boolean playPiano = false;
    public static boolean playDrum = false;

    public static void main(String[] args) {

        System.out.printf("Welcome to The House. You have 30 moves to escape. There is a set of verbs and nouns that will allow you to leave in the format of verb (open, close, light, read, write, play, look, get, go), noun (door, room, bench, chest, candle, note, matches, bookshelf, book, pen, scroll, music, trumpet, piano, drum, lock) or direction (north, south, east, west). If your move yields no advancement, the previous command will likely repeat itself. Good luck.\n\n");
        for (int i = 1; i < 31; i++) {
            gameUsage();
            movesLeft--;
            if (playTrumpet && playPiano && playDrum) {
                break;
            }
        }
        if (playTrumpet && playPiano && playDrum) {
            System.out.printf("\nCongratulations! You have completed the escape room challenge successfully in with %d moves to spare.\n", movesLeft);
        }
        else {
            System.out.printf("\nYou have failed the escape room challenge. Try again.");
        }
    }

    public static void gameUsage() {
        if (inFoyer) {
            foyer();
        }
        else if (inLibrary) {
            library();
        }
        else if (inConservatory) {
            conservatory();
        }
    }

    public static void foyer() {
        System.out.printf("%s You have %d moves left.\n>>", output, movesLeft);
        scan = new Scanner(System.in);
        String test = scan.nextLine();

        switch (test) {

            case "look bench":
                output = "There is a note on the bench.";
                break;

            case "read note":
                output = "The note says, 'May my light show you the way.'";
                break;

            case "look chest":
                if (chestOpen) {
                    output = "There are matches in the chest.";
                }
                else {
                    output = "the chest is closed.";
                }
                break;

            case "open chest":
                chestOpen = true;
                output = "The chest is now open. There is just a box of matches.";
                break;

            case "get matches":
                if (chestOpen) {
                    hasMatches = true;
                    output = "You have a box of matches.";
                }
                else {
                    output = "How can you see what is inside the chest? It's closed.";
                }
                break;

            case "look candle":
                if (candleLit) {
                    output = "The candle is burning, providing some light.";
                }
                else {
                    output = "The candle is unlit";
                }
                break;

            case "light candle":
                if (hasMatches) {
                    candleLit = true;
                    door1Locked = false;
                    output = "The candle is lit. You heard a metal grinding sound from the north.";
                }
                else {
                    output = "You don't have any matches.";
                }
                break;

            case "look door":
                if (!door1Locked) {
                    if (door1Open) {
                        output = "The door is unlocked and open.";
                    }
                    else {
                        output = "The door is unlocked, but closed.";
                    }
                }
                else {
                    output = "The door is locked.";
                }
                break;

            case "open door":
                if (!door1Locked) {
                    door1Open = true;
                    output = "The north door is open.";
                }
                else {
                    output = "The door is locked.";
                }
                break;

            case "go north":
                if (door1Open) {
                    inFoyer = false;
                    inLibrary = true;
                    output = "You have left the foyer. On your way out, the door slammed and locked. You are now in the library and cannot return to the foyer. In the library there are stacks of books lining the shelves, a desk, a pen, and a scroll. There is another locked door to the north.";
                }
                else {
                    output = "The door is closed.";
                }
                break;

            default :
                output = "You space out for a while pondering how to get out";
                break;
        }
    }

    public static void library() {
        System.out.printf("%s You have %d moves left.\n>>", output, movesLeft);
        scan = new Scanner(System.in);
        String test = scan.nextLine();

        switch (test) {

            case "look book":
                output = "The book is titled 'The Autobiography of ...' with the rest of the title empty.";
                break;

            case "look shelves":
            case "look shelf":
                output = "There is a grand book that sticks out from the others.";
                break;

            case "get pen":
                getPen = true;
                output = "You have acquired the pen.";
                break;

            case "read scroll":
            case "look scroll":
                output = "the scroll says, 'Share your story.'";
                break;

            case "get book":
                if (getBook) {
                    output = "You already have the book. Get busy writing.";
                }
                else {
                    output = "You now have the blank book. Time to get creative.";
                    getBook = true;
                }
                break;

            case "write book":
            case "write name":
                if (getPen && getBook) {
                    writeBook = true;
                    door2Locked = false;
                    output = "You have completed the autobiography You heard a metal grinding sound from the north.";
                }
                else {
                    output = "Get everything you need to write.";
                }
                break;

            case "look door":
                if (!door2Locked) {
                    if (door2Open) {
                        output = "The door is unlocked and open.";
                    }
                    else {
                        output = "The door is unlocked, but closed.";
                    }
                }
                else {
                    output = "The door is locked.";
                }
                break;

            case "open door":
                if (!door2Locked) {
                    door2Open = true;
                    output = "The north door is open.";
                }
                break;

            case "go north":
                if (door2Open) {
                    inLibrary = false;
                    inConservatory = true;
                    output = " You have left the library. On your way out, the door slammed and locked. You are now in the conservatory and cannot return to the library. In the conservatory there are there are three instruments: a trumpet, a piano, and a drum. There is a sheet of music on a stand.";
                }
                break;

            default:
                output = "You space out for a while pondering how to get out";
        }
    }

    public static void conservatory()  {
        System.out.printf("%s You have %d moves left.\n>>", output, movesLeft);
        scan = new Scanner(System.in);
        String test = scan.nextLine();

        switch (test) {
            case "read music":
                output = "'Timbre, Tone, Time.'";
                break;

            case "play trumpet":
                if (!playPiano && !playDrum) {
                    playTrumpet = true;
                    output = "Good thing you can play the trumpet. That was a perfect timbre.";
                }
                else {
                    output = "Hmm... Something seems to be wrong with the trumpet. It isn't playing correctly.";
                    playTrumpet = false;
                    playPiano = false;
                    playDrum = false;
                }
                break;

            case "play piano":
                if (playTrumpet && !playDrum) {
                    playPiano = true;
                    output = "Good thing you can play the piano. That was a perfect tone.";
                }
                else {
                    output = "Hmm... Something seems to be wrong with the piano. It isn't playing correctly.";
                    playTrumpet = false;
                    playPiano = false;
                    playDrum = false;
                }
                break;

            case "play drum":
                if (playTrumpet && playPiano) {
                    playDrum = true;
                    output = "Good thing you can play the drums. That was a perfect time.";
                }
                else {
                    output = "Hmm... Something seems to be wrong with the drum. It isn't playing correctly.";
                    playTrumpet = false;
                    playPiano = false;
                    playDrum = false;
                }
                break;

            default:
                output = "You space out for a while pondering how to get out";
        }
    }
}