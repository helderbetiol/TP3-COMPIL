grammar Calc;

// syntactic rules

program  : funcDef* body
         ;
funcDef  : '(' 'defun' head body ')'
         ;
head     : '(' functionId variableId* ')'
         ;
body     : varDef* expression
         ;
varDef   : '(' '=' variableId expression ')'
         ;
expression : INTEGER # IntLit
           | variableId # VarId
//           | '(' OP expression tail
           | '(' '-' expression tail # UnExp
           | '(' OP expression expression ')' # BinExp
           | '(' 'if' expression expression expression ')' # CondExp
           | '(' functionId expression* ')' # FunCall
           ;
tail       : ')' # UnExp2 // - unaire
           | expression ')' # BinExp2 // - binaire
           ;
variableId : IDENTIFIER
           ;
functionId : IDENTIFIER
           ;

// lexical rules

OP       : '+' | '-' | '*' | '/' | '==' | '<'
         ;
IDENTIFIER : ('a'..'z')('a'..'z' | '0'..'9')*
         ;
INTEGER  : '0' | ('1'..'9')('0'..'9')*
         ;
WS       : [ \t\n\r]+ -> channel(HIDDEN)
         ;
LINE_COMMENT : '//' ~'\n'* '\n' -> channel(HIDDEN)
         ;