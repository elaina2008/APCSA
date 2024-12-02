/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author Elaina Pan
 *	@version 11.28.24
 */
 import java.util.Scanner;
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array
	private int tokenCount; // number of tokens in array
	
	// constants
	private final int MAX_LENGTH = 80; //maximum length of one line on screen
	private final int H1_MAX_LENGTH = 40; // MAX_LENGTH for H1
	private final int H2_MAX_LENGTH = 50; // MAX_LENGTH for H2
	private final int H3_MAX_LENGTH = 60; // MAX_LENGTH for H3
	private final int H4_MAX_LENGTH = 80; // MAX_LENGTH for H4
	private final int H5_MAX_LENGTH = 100; // MAX_LENGTH for H5
	private final int H6_MAX_LENGTH = 120; // MAX_LENGTH for H6

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	
	// constructor initializes variables
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		tokenCount = 0;
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
	}
	
	// first use HTMLUtilities to tokenize HTML file then prints out into GUI screen
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		hf.openAndTokenizeHTML(args);
		hf.processTokens();
	}
	// uses HTMLUtilities to tokenize file and put all words into an array
	public void openAndTokenizeHTML(String [] args) {
		HTMLUtilities util = new HTMLUtilities();
		Scanner input = null;
		String fileName = "";
		// if the command line contains the file name, then store it
		if (args.length > 0)
			fileName = args[0];
		// otherwise print out usage message
		else {
			System.out.println("Usage: java HTMLTester <htmlFileName>");
			System.exit(0);
		}
		input = FileUtils.openToRead(fileName);
		int tokenNum = 0;
		// Read each line of the HTML file, tokenize, then print tokens
		while (input.hasNext()) {
			String line = input.nextLine();
			String [] tokens = util.tokenizeHTMLString(line);
			for(int i=0;i<tokens.length;i++) {
				this.tokens[tokenNum+i] = tokens[i];
			}
			tokenNum += tokens.length;
		}
		input.close();
		tokenCount = tokenNum;
	}
	// goes through the token array to print out onto GUI
	public void processTokens() {
		//if(!tokens[0].equalsIgnoreCase("<html>") 
		//|| !tokens[1].equalsIgnoreCase("<body>") 
		//|| !tokens[tokenCount-2].equalsIgnoreCase("</body>") 
		//|| !tokens[tokenCount-1].equalsIgnoreCase("</html>")) System.exit(1);
		int i = 2; // index of token array
		int lineLength = 0; // to prevent each line from being too long
		
		enum Format { NONE, PARAGRAPH, BOLD, ITALIC, QUOTE, H1, H2, H3, 
			H4, H5, H6, PREFORMAT};
		Format format = Format.NONE;
		while(i < tokenCount-2) {
			// check for paragraph
			if(format != Format.PREFORMAT) tokens[i] = tokens[i].trim();
			if(tokens[i].equalsIgnoreCase("</p>")) { 
				browser.println();
				browser.println();
				lineLength = 0;
				format = Format.NONE;
			}
			else if(tokens[i].equalsIgnoreCase("<p>")) {
				browser.println();
				browser.println();
				lineLength = 0;
				format = Format.PARAGRAPH;
			}
			// check for quote
			else if(tokens[i].equalsIgnoreCase("<q>")) {
				browser.print("\" ");
				format = Format.QUOTE;
			}
			else if(tokens[i].equalsIgnoreCase("</q>")) {
				browser.print("\" ");
				format = Format.NONE;
			}
			// check for bold
			else if(tokens[i].equalsIgnoreCase("<b>")) 
				format = Format.BOLD;
			// check for italic
			else if(tokens[i].equalsIgnoreCase("<i>")) 
				format = Format.ITALIC;
			// check for preformat
			else if(tokens[i].equalsIgnoreCase("<pre>")) {
				browser.println();
				format = Format.PREFORMAT;
			}
			else if(tokens[i].equalsIgnoreCase("</pre>")) {
					browser.println();
				lineLength = 0;
				format = Format.NONE;
			}
			// check for any header
			else if(tokens[i].equalsIgnoreCase("<h1>")) {
				browser.println();
				browser.println();
				lineLength = 0;
				format = Format.H1;
			}
			else if(tokens[i].equalsIgnoreCase("<h2>")) {
				browser.println();
				lineLength = 0;
				format = Format.H2;
			}
			else if(tokens[i].equalsIgnoreCase("<h3>")) {
				browser.println();
				lineLength = 0;
				format = Format.H3;
			}
			else if(tokens[i].equalsIgnoreCase("<h4>")) {
				browser.println();
				lineLength = 0;
				format = Format.H4;
			}
			else if(tokens[i].equalsIgnoreCase("<h5>")) {
				browser.println();
				lineLength = 0;
				format = Format.H5;
			}
			else if(tokens[i].equalsIgnoreCase("<h6>")) {
				browser.println();
				lineLength = 0;
				format = Format.H6;
			}
			else if (tokens[i].equalsIgnoreCase("</h1>") || 
			tokens[i].equalsIgnoreCase("</h2>") || 
			tokens[i].equalsIgnoreCase("</h3>") || 
			tokens[i].equalsIgnoreCase("</h4>") || 
			tokens[i].equalsIgnoreCase("</h5>") || 
			tokens[i].equalsIgnoreCase("</h6>")) {
				browser.println();
				lineLength = 0;
				format = Format.NONE;
			}
			// print horizontal rule
			else if(tokens[i].equalsIgnoreCase("<hr>")) {
				browser.printHorizontalRule();
				lineLength = 0;
			}
			// print break
			else if(tokens[i].equalsIgnoreCase("<br>")) {
				browser.printBreak();
				lineLength = 0;
			}
			// for other tokens
			else {
				switch(format) {
					// print regularly for none/quote/paragraph
					case PARAGRAPH: case QUOTE: case NONE:
					if(i < tokenCount) {
						if(lineLength+tokens[i].length() >= MAX_LENGTH) {
							browser.println();
							lineLength = 0;
						}
						browser.print(tokens[i]);
						lineLength += tokens[i].length();
						if(i+1 >= tokenCount ||
						!tokens[i+1].equalsIgnoreCase("</q>") 
						&& (tokens[i+1].length()!=1
						|| !isPunctuation(tokens[i+1].charAt(0)))) {
							browser.print(" ");
							lineLength++;
						}
						break;
					}
					case BOLD: // print bold
					if(i < tokenCount) {
						if(tokens[i].equalsIgnoreCase("</b>")) {
							format = Format.NONE;
						}
						else {
							if(lineLength+tokens[i].length() >= MAX_LENGTH) {
								lineLength = 0;
								browser.println();
							}
							browser.printBold(tokens[i]);
							lineLength += tokens[i].length();
							if(i+1 >= tokenCount || tokens[i+1].length()!=1
							|| !isPunctuation(tokens[i+1].charAt(0))) {
								lineLength++;
								browser.printBold(" ");
							}
							break;
						}
					}
					break;
					case ITALIC: // print italic
					if(i < tokenCount) {
						if(tokens[i].equalsIgnoreCase("</i>")) {
							format = Format.NONE;
						}
						else {
							if(lineLength+tokens[i].length() >= MAX_LENGTH) {
								browser.println();
								lineLength = 0;
							}
							browser.printItalic(tokens[i]);
							lineLength += tokens[i].length();
							if(i+1 >= tokenCount || tokens[i+1].length()!=1
							|| !isPunctuation(tokens[i+1].charAt(0))) {
								lineLength++;
								browser.printItalic(" ");
							}
						}
					}
					break;
					case PREFORMAT: // print preformat
					browser.printPreformattedText(tokens[i]);
					browser.println();
					browser.println();
					lineLength = 0;
					break;
					// for headers
					case H1:
					if(lineLength+tokens[i].length() >= H1_MAX_LENGTH) {
						browser.println();
						lineLength = 0;
						}
					browser.printHeading1(tokens[i]);
					lineLength += tokens[i].length();
					if(i+1 >= tokenCount || tokens[i+1].length()!=1
					|| !isPunctuation(tokens[i+1].charAt(0))) {
						lineLength++;
						browser.printHeading1(" ");
					}
					break;
					case H2:
					if(lineLength+tokens[i].length() >= H2_MAX_LENGTH) {
						browser.println();
						lineLength = 0;
					}
					browser.printHeading2(tokens[i]);
					lineLength += tokens[i].length();
					if(i+1 >= tokenCount || tokens[i+1].length()!=1
					|| !isPunctuation(tokens[i+1].charAt(0))) {
						lineLength++;
						browser.printHeading2(" ");
					}
					break;
					case H3:
					if(lineLength+tokens[i].length() >= H3_MAX_LENGTH) {
						browser.println();
						lineLength = 0;
					}
					browser.printHeading3(tokens[i]);
					lineLength += tokens[i].length();
					if(i+1 >= tokenCount || tokens[i+1].length()!=1
					|| !isPunctuation(tokens[i+1].charAt(0))) {
						lineLength++;
						browser.printHeading3(" ");
					}
					break;
					case H4:
					if(lineLength+tokens[i].length() >= H4_MAX_LENGTH) {
						browser.println();
						lineLength = 0;
					}
					browser.printHeading4(tokens[i]);
					lineLength += tokens[i].length();
					if(i+1 >= tokenCount || tokens[i+1].length()!=1
					|| !isPunctuation(tokens[i+1].charAt(0))) {
						lineLength++;
						browser.printHeading4(" ");
					}
					break;
					case H5:
					if(lineLength+tokens[i].length() >= H5_MAX_LENGTH) {
						browser.println();
						lineLength = 0;
					}
					browser.printHeading5(tokens[i]);
					lineLength += tokens[i].length();
					if(i+1 >= tokenCount || tokens[i+1].length()!=1
					|| !isPunctuation(tokens[i+1].charAt(0))) {
						lineLength++;
						browser.printHeading5(" ");
					}
					break;
					case H6:
					if(lineLength+tokens[i].length() >= H6_MAX_LENGTH) {
						browser.println();
						browser.println();
						lineLength = 0;
					}
					browser.printHeading6(tokens[i]);
					lineLength += tokens[i].length();
					if(i+1 >= tokenCount || tokens[i+1].length()!=1
					|| !isPunctuation(tokens[i+1].charAt(0))) {
						lineLength++;
						browser.printHeading6(" ");
					}
					break;
				}
			}
			i++;
		}
	}
	/**
	 * parallels isDigit()/etc, detects whether character is punctuation
	 * @param c		character you are checking
	 */
	private boolean isPunctuation(char c) {
		char[] punctuations = { '.', ',', ';', ':', '(', ')', '?',
					'!', '=', '&', '~', '+', '-'};
		for(int i=0;i<punctuations.length;i++) {
			if(c == punctuations[i]) return true;
		}
		return false;
	}
}
