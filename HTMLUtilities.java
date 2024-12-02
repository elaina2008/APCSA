/**
 *	Utilities for handling HTML
 *
 *	@author	Elaina Pan
 *	@since	11.14.24
 */
public class HTMLUtilities {

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	// NONE = not nested in a block, COMMENT = inside a comment block
	// PREFORMAT = inside a pre-format block
	private enum TokenState { NONE, COMMENT, PREFORMAT};
	// the current tokenizer state
	private TokenState state; 
	// constructor to default token state as NONE
	public HTMLUtilities() {
		state = TokenState.NONE;
	}
	/**
	 * breaks up string into tokens
	 * @param str 	string you are breaking up
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		int index = 0;
		int index2 = 0;
		int tokenCount = 0;
		// check for preformat
		if(str.equals("<pre>")) {
			state = TokenState.PREFORMAT;
			result[tokenCount] = "<pre>";
			tokenCount++;
		}
		else if(str.trim().equals("</pre>")) {
			state = TokenState.NONE;
			result[tokenCount] = "</pre>";
			tokenCount++;
		}
		else if(state == TokenState.PREFORMAT) {
			result[tokenCount] = str;
			tokenCount++;
		}	
		// cycles through full string to break up into tokens
		else while(index < str.length()) {
			String token = "";
			if(index+4 < str.length() && state == TokenState.NONE
			&& str.substring(index,index+"<!--".length()).equals("<!--")) {
				state = TokenState.COMMENT;
			}
			if(state == TokenState.COMMENT) {
				index++;
			}
			else if(str.equals("</pre>")) {
				state = TokenState.NONE;
			}
			else if(Character.isWhitespace(str.charAt(index))) index++;
			else {
				if(str.charAt(index) == '<') {
					index2 = str.indexOf(">", index)+1;
				}
				else if(Character.isLetter(str.charAt(index))) {
					index2 = index+1;
					while(index2 < str.length() && 
						(Character.isLetter(str.charAt(index2)) || 
						str.charAt(index2) == '-' 
						&& index2+1<str.length() &&
						Character.isLetter(str.charAt(index2+1)))) {
						 index2++;
					}
				}
				else if(Character.isDigit(str.charAt(index)) ||
					str.charAt(index) == '-' && index+1<str.length()
					&& Character.isDigit(str.charAt(index+1))) {
					index2 = index;
					boolean valid = index2 < str.length();
					while(valid) {
						index2++;
						valid = index2<str.length() &&
						Character.isDigit(str.charAt(index2));
						valid = valid || index2+1<str.length() && 
						(str.charAt(index2) == '.' || str.charAt(index2) == 'e')
						 && Character.isDigit(str.charAt(index2+1));
						boolean valid3 = index2+2<str.length() && 
						str.charAt(index2) == 'e' && str.charAt(index2+1) == '-'
						&& Character.isDigit(str.charAt(index2+2));
						if(valid3) index2++;
						valid = valid || valid3;
						valid = valid && index2 < str.length();
					}
				}
				else if(isPunctuation(str.charAt(index))) {
					index2 = index + 1;
				}
				result[tokenCount] = str.substring(index, index2).trim();
				index = index2;
				tokenCount++;
			}
			if(state == TokenState.COMMENT && str.indexOf("-->",index)!=-1) {
				index = str.indexOf("-->",index)+"-->".length();
				state = TokenState.NONE;
			}
			
		}
		//return the correctly sized array
		String[] result2 = new String[tokenCount];
		for(int i=0;i<tokenCount;i++) {
			result2[i] = result[i];
		}
		return result2;
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
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}
}
