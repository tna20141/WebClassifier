/* The following code was generated by JFlex 1.5.1 */

package classifier;


import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.tartarus.snowball.ext.PorterStemmer;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.5.1
 * from the specification file <tt>template.flex</tt>
 */
class Tokenizer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int TAG = 2;
  public static final int WORD = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\0\1\0\1\0\1\0\1\0\22\0\1\0\3\0\1\3"+
    "\27\0\1\1\1\0\1\2\2\0\32\3\4\0\1\3\1\0\25\3"+
    "\1\3\4\3\47\0\4\3\4\0\1\3\12\0\1\3\4\0\1\3"+
    "\5\0\27\3\1\0\37\3\1\0\u01ca\3\4\0\14\3\16\0\5\3"+
    "\7\0\1\3\1\0\1\3\201\0\5\3\1\0\2\3\2\0\4\3"+
    "\10\0\1\3\1\0\3\3\1\0\1\3\1\0\24\3\1\0\123\3"+
    "\1\0\213\3\10\0\236\3\11\0\46\3\2\0\1\3\7\0\47\3"+
    "\110\0\33\3\5\0\3\3\30\0\1\3\24\0\53\3\43\0\2\3"+
    "\1\0\143\3\1\0\1\3\17\0\2\3\7\0\2\3\12\0\3\3"+
    "\2\0\1\3\20\0\1\3\1\0\36\3\35\0\131\3\13\0\1\3"+
    "\30\0\41\3\11\0\2\3\4\0\1\3\5\0\26\3\4\0\1\3"+
    "\11\0\1\3\3\0\1\3\27\0\31\3\253\0\66\3\3\0\1\3"+
    "\22\0\1\3\7\0\12\3\17\0\7\3\1\0\7\3\5\0\10\3"+
    "\2\0\2\3\2\0\26\3\1\0\7\3\1\0\1\3\3\0\4\3"+
    "\3\0\1\3\20\0\1\3\15\0\2\3\1\0\3\3\16\0\4\3"+
    "\7\0\1\3\11\0\6\3\4\0\2\3\2\0\26\3\1\0\7\3"+
    "\1\0\2\3\1\0\2\3\1\0\2\3\37\0\4\3\1\0\1\3"+
    "\23\0\3\3\20\0\11\3\1\0\3\3\1\0\26\3\1\0\7\3"+
    "\1\0\2\3\1\0\5\3\3\0\1\3\22\0\1\3\17\0\2\3"+
    "\17\0\1\3\23\0\10\3\2\0\2\3\2\0\26\3\1\0\7\3"+
    "\1\0\2\3\1\0\5\3\3\0\1\3\36\0\2\3\1\0\3\3"+
    "\17\0\1\3\21\0\1\3\1\0\6\3\3\0\3\3\1\0\4\3"+
    "\3\0\2\3\1\0\1\3\1\0\2\3\3\0\2\3\3\0\3\3"+
    "\3\0\14\3\26\0\1\3\50\0\1\3\13\0\10\3\1\0\3\3"+
    "\1\0\27\3\1\0\12\3\1\0\5\3\3\0\1\3\32\0\2\3"+
    "\6\0\2\3\43\0\10\3\1\0\3\3\1\0\27\3\1\0\12\3"+
    "\1\0\5\3\3\0\1\3\40\0\1\3\1\0\2\3\17\0\2\3"+
    "\22\0\10\3\1\0\3\3\1\0\51\3\2\0\1\3\20\0\1\3"+
    "\21\0\2\3\30\0\6\3\5\0\22\3\3\0\30\3\1\0\11\3"+
    "\1\0\1\3\2\0\7\3\72\0\60\3\1\0\2\3\13\0\10\3"+
    "\72\0\2\3\1\0\1\3\2\0\2\3\1\0\1\3\2\0\1\3"+
    "\6\0\4\3\1\0\7\3\1\0\3\3\1\0\1\3\1\0\1\3"+
    "\2\0\2\3\1\0\4\3\1\0\2\3\11\0\1\3\2\0\5\3"+
    "\1\0\1\3\25\0\2\3\42\0\1\3\77\0\10\3\1\0\44\3"+
    "\33\0\5\3\163\0\53\3\24\0\1\3\20\0\6\3\4\0\4\3"+
    "\3\0\1\3\3\0\2\3\7\0\3\3\4\0\15\3\14\0\1\3"+
    "\21\0\46\3\12\0\53\3\1\0\1\3\3\0\u0149\3\1\0\4\3"+
    "\2\0\7\3\1\0\1\3\1\0\4\3\2\0\51\3\1\0\4\3"+
    "\2\0\41\3\1\0\4\3\2\0\7\3\1\0\1\3\1\0\4\3"+
    "\2\0\17\3\1\0\71\3\1\0\4\3\2\0\103\3\45\0\20\3"+
    "\20\0\125\3\14\0\u026c\3\2\0\21\3\1\0\32\3\5\0\113\3"+
    "\3\0\3\3\17\0\15\3\1\0\4\3\16\0\22\3\16\0\22\3"+
    "\16\0\15\3\1\0\3\3\17\0\64\3\43\0\1\3\3\0\2\3"+
    "\103\0\130\3\10\0\51\3\1\0\1\3\5\0\106\3\12\0\35\3"+
    "\63\0\36\3\2\0\5\3\13\0\54\3\25\0\7\3\70\0\27\3"+
    "\11\0\65\3\122\0\1\3\135\0\57\3\21\0\7\3\67\0\36\3"+
    "\15\0\2\3\20\0\46\3\32\0\44\3\51\0\3\3\12\0\44\3"+
    "\153\0\4\3\1\0\4\3\16\0\300\3\100\0\u0116\3\2\0\6\3"+
    "\2\0\46\3\2\0\6\3\2\0\10\3\1\0\1\3\1\0\1\3"+
    "\1\0\1\3\1\0\37\3\2\0\65\3\1\0\7\3\1\0\1\3"+
    "\3\0\3\3\1\0\7\3\3\0\4\3\2\0\6\3\4\0\15\3"+
    "\5\0\3\3\1\0\7\3\102\0\2\3\23\0\1\3\34\0\1\3"+
    "\15\0\1\3\20\0\15\3\3\0\32\3\110\0\1\3\4\0\1\3"+
    "\2\0\12\3\1\0\1\3\3\0\5\3\6\0\1\3\1\0\1\3"+
    "\1\0\1\3\1\0\4\3\1\0\13\3\2\0\4\3\5\0\5\3"+
    "\4\0\1\3\21\0\51\3\u0a77\0\57\3\1\0\57\3\1\0\205\3"+
    "\6\0\4\3\21\0\46\3\12\0\66\3\11\0\1\3\20\0\27\3"+
    "\11\0\7\3\1\0\7\3\1\0\7\3\1\0\7\3\1\0\7\3"+
    "\1\0\7\3\1\0\7\3\1\0\7\3\120\0\1\3\u01d5\0\3\3"+
    "\31\0\11\3\7\0\5\3\2\0\5\3\4\0\126\3\6\0\3\3"+
    "\1\0\132\3\1\0\4\3\5\0\51\3\3\0\136\3\21\0\33\3"+
    "\65\0\20\3\u0200\0\u19b6\3\112\0\u51cc\3\64\0\u048d\3\103\0\56\3"+
    "\2\0\u010d\3\3\0\20\3\12\0\2\3\24\0\57\3\20\0\31\3"+
    "\10\0\120\3\47\0\11\3\2\0\147\3\2\0\4\3\1\0\2\3"+
    "\16\0\12\3\120\0\10\3\1\0\3\3\1\0\4\3\1\0\27\3"+
    "\25\0\1\3\7\0\64\3\16\0\62\3\76\0\6\3\3\0\1\3"+
    "\16\0\34\3\12\0\27\3\31\0\35\3\7\0\57\3\34\0\1\3"+
    "\60\0\51\3\27\0\3\3\1\0\10\3\24\0\27\3\3\0\1\3"+
    "\5\0\60\3\1\0\1\3\3\0\2\3\2\0\5\3\2\0\1\3"+
    "\1\0\1\3\30\0\3\3\43\0\6\3\2\0\6\3\2\0\6\3"+
    "\11\0\7\3\1\0\7\3\221\0\43\3\35\0\u2ba4\3\14\0\27\3"+
    "\4\0\61\3\u2104\0\u012e\3\2\0\76\3\2\0\152\3\46\0\7\3"+
    "\14\0\5\3\5\0\1\3\1\0\12\3\1\0\15\3\1\0\5\3"+
    "\1\0\1\3\1\0\2\3\1\0\2\3\1\0\154\3\41\0\u016b\3"+
    "\22\0\100\3\2\0\66\3\50\0\15\3\66\0\2\3\30\0\3\3"+
    "\31\0\1\3\6\0\5\3\1\0\207\3\7\0\1\3\34\0\32\3"+
    "\4\0\1\3\1\0\32\3\13\0\131\3\3\0\6\3\2\0\6\3"+
    "\2\0\6\3\2\0\3\3\3\0\2\3\3\0\2\3\31\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\4\1\1";

  private static int [] zzUnpackAction() {
    int [] result = new int[7];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\4\0\10\0\14\0\14\0\14\0\20";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[7];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\4\4\1\6\2\4\1\5\1\4\1\7"+
    "\7\0\1\7";

  private static int [] zzUnpackTrans() {
    int [] result = new int[20];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\1\3\11\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[7];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Tokenizer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Tokenizer(java.io.InputStream in) {
    this(new java.io.InputStreamReader
             (in, java.nio.charset.Charset.forName("UTF-8")));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 1558) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

    // numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int parse() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

        if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return YYEOF;
        }

        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 1: 
          { foundWord();
          }
        case 5: break;
        case 2: 
          { 
          }
        case 6: break;
        case 3: 
          { tagStart();yybegin(TAG);
          }
        case 7: break;
        case 4: 
          { tagEnd();yybegin(WORD);
          }
        case 8: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return YYEOF;
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }

    private static PorterStemmer stemmer = new PorterStemmer();
    private static CharArraySet stopWords = StopAnalyzer.ENGLISH_STOP_WORDS_SET;

    protected static String filter(String word) {
        if (word.length() < 2)
            return "";

        word = word.toLowerCase();

        if (Tokenizer.stopWords.contains(word))
            return "";

        Tokenizer.stemmer.setCurrent(word);
        stemmer.stem();
        word = stemmer.getCurrent();
        return word;
    }

  protected void tagStart() {

  }

  protected void tagEnd() {

  }

  protected void foundWord() {

  }


}
