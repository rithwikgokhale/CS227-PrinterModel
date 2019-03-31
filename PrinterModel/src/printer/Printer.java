package printer;

/*
 * Rithwik Gokhale
 * ID: 831106304
 * ComS 227
 * Homework Assignment 1
 * Printer Model
 * @rgokhale
 */

public class Printer {

	/**
	 * Capacity, in ounces, of a new ink cartridge.
	 */
	public static final double INK_CAPACITY = 2.0;

	/**
	 * Amount of ink, in ounces, used per printed page.
	 */
	public static final double INK_USAGE = 0.0023;

	private int Capacity; // This variable holds the same value as the maximum paper capacity of the
							// printer
	private int numPaper; // This variable holds the amount of paper currently in the printer as a certain
							// command is being executed
	private double ink; // This variable holds the amount of ink currently in the printer as a certain
						// command is being executed
	private int totalPaper; // This variable holds the value for the total amount of paper printed by the
							// printer since its construction

	/**
	 * The constructor below establishes the printer model. The user input here will
	 * be the paper capacity of the printer. The ink capacity of the printer is
	 * already established.
	 * 
	 * @param givenCapacity 
	 * 		The user established of the capacity of the paper for
	 *                      this printer
	 */
	public Printer(int givenCapacity) {
		Capacity = givenCapacity;
		numPaper = 0; // Since no paper is added by the user in this case, the number of paper will be
						// 0
		ink = INK_CAPACITY;

		totalPaper = 0;

	}

	/**
	 * This constructor will also establish the printer model with an additional
	 * user input. Along with the paper capacity of the printer, there will also be
	 * a second input which is the additional paper which is added to the printer.
	 * The amount of ink in this model is still initialized to the pre-established
	 * ink value
	 * 
	 * 
	 * @param givenCapacity       
	 * 		The paper capacity of this printer
	 * @param givenNumberOfSheets 
	 * 		The number of sheets of paper which are present in
	 *                            the printer
	 */
	public Printer(int givenCapacity, int givenNumberOfSheets) {
		Capacity = givenCapacity;
		numPaper = givenNumberOfSheets;
		ink = INK_CAPACITY;
		numPaper = Math.min(givenCapacity, givenNumberOfSheets); // this command ensures that the user cannot add more
																	// paper than the max capacity of the printer

		totalPaper = 0;
	}

	/**
	 * This public method has one user input which takes in the additional paper
	 * which is added by the user to the printer model. There is an additional
	 * command here which ensures that the paper value does not cross the printer
	 * limit
	 * 
	 * @param additionalSheets 
	 * 		The number of sheets of paper which are added to the
	 *                         printer model
	 */
	public void addPaper(int additionalSheets) {

		totalPaper = numPaper + additionalSheets;
		numPaper = Math.min(totalPaper, Capacity); // This command ensures that the number of sheets does not cross the
													// printer model's limit
	}

	/**
	 * This public method returns the amount of paper which is which is currently in
	 * the printer. The use of a math function ensures that the value can never be
	 * less than zero
	 * 
	 * @return Returns the number of sheets of paper which is currently present in
	 *         the printer
	 */
	public int getCurrentPaper() {
		return Math.max(numPaper, 0); // The math command ensures that the user value can never be less than zero

	}

	/**
	 * This public command returns the total amount of sheets which has been printed
	 * by the paper since the construction of the printer model. This includes all
	 * the paper printed from all the previous commands too
	 * 
	 * @return 
	 * 		The total amount of paper which has been printed from this printer
	 *         since its construction
	 */
	public int getTotalPaperUse() {
		return totalPaper;
	}

	/**
	 * This public command returns a boolean value representing whether the printer
	 * is out of ink or not. This is done by comparing the ink value to the amount
	 * of ink needed to print one sheet. The result from this comparison will decide
	 * if the printer is out of ink
	 * 
	 * @return 
	 * 		Returns true if the printer does not have enough ink to print even
	 *         one page
	 */
	public boolean isInkOut() {
		if (ink < INK_USAGE)
			return true;
		else
			return false;
	}

	/**
	 * This will be a very simple public method which will replace the ink cartridge
	 * by changing the ink value to the original ink capacity.
	 * 
	 */
	public void replaceInk() {
		ink = INK_CAPACITY;
	}

	/**
	 * This is the main public method which actually prints. The user input in this
	 * case will be the amount of pages to be printed. This method will then
	 * systematically subtract the ink and the paper used for this task from the
	 * overall number of pages and the overall amount of ink.
	 * 
	 * @param numberOfPages 
	 * 		The number of pages which is being printed. Note that
	 *                      this is only one side of the sheet of paper
	 */
	public void print(int numberOfPages) {

		int paperInitial = numPaper;
		int paperFinal = paperInitial - numberOfPages;
		// paperInitial and paperFinal are temporary variables which are used to
		// simplify the process of printing the pages. They do not exist outside of this
		// method.
		numPaper = Math.max(paperFinal, 0);
		ink = ink - (INK_USAGE * Math.min(numberOfPages, paperInitial));
		totalPaper = (paperInitial - numPaper) + totalPaper;

	}

	/**
	 * This is the second print public method. The difference in this method is that
	 * the sheet of paper will print on both sides. The difference between this and
	 * the previous public method is the difference in the amount of ink and paper
	 * used for printing on both sides of the sheet of paper. The user input in this
	 * case is again the amount of pages which has to be printed and the only
	 * difference in this case is the amount of paper and ink used compared to the
	 * previous public method
	 * 
	 * @param numberOfPages 
	 * 		The the number of pages which is bring printed. Note
	 *                      that this is on both sides of the sheet of paper
	 */
	public void printTwoSided(int numberOfPages) {

		int paperInitial = numPaper;
		double paperFinal = (paperInitial - numberOfPages * 0.5);
		// paperInitial and paperFinal are temporary variables which are used to
		// simplify the process of printing the pages. They do not exist outside of this
		// method.
		numPaper = (int) Math.max(paperFinal, 0);
		ink = ink - (INK_USAGE * Math.min(numberOfPages, paperInitial));
		paperFinal = paperInitial - numPaper;
		totalPaper = (int) (paperFinal + totalPaper);
	}

}
