grammar FullIRC;

nick
    : CHARACTER (CHARACTER | INTEGER | SPECIAL)+
    ;

channel
    : ('#' | '&') CHARACTER+
    ;

mask
    : ('#' | '$') CHARACTER+
    ;

CHARACTER
    : 'a'..'z'
    | 'A'..'Z'
    ;

INTEGER
    : '0'..'9'
    ;

SPECIAL
    : '!'
    ;