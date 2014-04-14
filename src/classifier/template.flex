package classifier;

%%

%class Tokenizer
%function parse
%int

LineTerminator = \r|\n|\r\n
TagStart = <
TagEnd = >
WhiteSpace = {LineTerminator} | [ \t\f\v]

Word = [:jletter:]*

%state TAG
%state WORD

%%

<YYINITIAL> {
	{TagStart}		{ System.out.println("Tag start");yybegin(TAG); }
	[^]			{ }
}

<TAG> {
	{TagEnd}		{ System.out.println("Tag end");yybegin(WORD); }
	[^]			{ }
}

<WORD> {
	{Word}			{ System.out.println(yytext()); }
	{TagStart}		{ System.out.println("Tag start");yybegin(TAG); }
	[^]			{ }
}
