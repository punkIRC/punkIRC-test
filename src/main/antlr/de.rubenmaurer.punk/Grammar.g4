grammar Grammar;

/*
 * Parser Rules
 */
def
    : 'PRICEFIELD'
    ;

server_response
    : DELIMITER server WHITESPACE code WHITESPACE nick
    ;

server
    : WORD
    ;

code
    : INTEGER
    ;

nick
    : WORD
    ;

welcome             : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE DELIMITER 'Welcome to the Internet Relay Network' WHITESPACE address;
unknown             : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE command WHITESPACE DELIMITER 'Unknown command';
noSuchNick          : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE target WHITESPACE DELIMITER 'No such nick/channel';
ping                : 'PONG';

// IRC PRIVMSG & NOTICE replies
private_message     : DELIMITER CLIENT PRIVMSG DELIMITER PLAIN;
notice              : DELIMITER CLIENT NOTICE DELIMITER PLAIN;

rpl_WHOISUSER       : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE target WHITESPACE user WHITESPACE host WHITESPACE '*' WHITESPACE DELIMITER fullname;
rpl_WHOISSERVER     : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE target WHITESPACE server WHITESPACE DELIMITER server_version;
rpl_ENDOFWHOIS      : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE target WHITESPACE DELIMITER 'End of WHOIS list';

rpl_LUSERCLIENT     : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE DELIMITER 'There are' WHITESPACE INTEGER WHITESPACE 'users and' WHITESPACE INTEGER WHITESPACE 'services on' WHITESPACE INTEGER WHITESPACE 'servers';
rpl_LUSEROP         : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE INTEGER WHITESPACE DELIMITER 'operator(s) online';
rpl_LUSERUNKNOWN    : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE INTEGER WHITESPACE DELIMITER 'unknown connection(s)';
rpl_LUSERCHANNELS   : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE INTEGER WHITESPACE DELIMITER 'channels formed';
rpl_LUSERME         : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE DELIMITER 'I have' WHITESPACE INTEGER WHITESPACE 'clients and' WHITESPACE INTEGER WHITESPACE 'servers';

rpl_MOTDSTART       : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE DELIMITER '-' WHITESPACE server WHITESPACE 'Message of the day' WHITESPACE '-';
rpl_MOTD            : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE DELIMITER '-' WHITESPACE motd;
rpl_ENDOFMOTD       : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE DELIMITER 'End of MOTD command';

rpl_QUIT            : 'ERROR' WHITESPACE DELIMITER 'Closing Link' DELIMITER WHITESPACE server WHITESPACE '(' message ')';

// IRC JOIN replies
join_reply          : DELIMITER WORD 'JOIN' WORD;
join_name_reply     : server_response '=' WORD DELIMITER WORD;
join_name_end_reply : server_response WORD DELIMITER 'End of NAMES list';

// IRC ERROR replies
no_such_channel     : server_response WHITESPACE CHANNEL DELIMITER 'No such channel'
                    ;

cannot_send_to_chnl : server_response WHITESPACE CHANNEL WHITESPACE DELIMITER 'Cannot send to channel'
                    ;

err_NOMOTD          : DELIMITER server WHITESPACE code WHITESPACE nick WHITESPACE DELIMITER 'MOTD File is missing';
err_NICKNAMEINUSE   : DELIMITER server WHITESPACE code WHITESPACE '*' WHITESPACE nick WHITESPACE DELIMITER 'Nickname is already in use';

channel_user_name   : WORD;
host                : WORD;
user                : WORD;
message             : (WORD | WHITESPACE)+?;
delimiter           : DELIMITER;
address             : WORD;
command             : WORD;
target              : WORD;
fullname            : (WORD | WHITESPACE)+?;
server_version      : (WORD | WHITESPACE)+?;
motd                : (WORD | WHITESPACE)+?;

/*
 * Lexer Rules
 */
WHITESPACE          : (' ' | '\t')+
                    ;

PRIVMSG : 'PRIVMSG' WHITESPACE TARGET WHITESPACE
        ;

NOTICE  : 'NOTICE' WHITESPACE TARGET WHITESPACE
        ;

TARGET  : TEXT
        | CHANNEL
        ;

CHANNEL : (CHAR | SPECIAL)+
        ;

WORD    : CHAR+
        ;

TEXT    : WORD+
        ;

PLAIN   : TEXT+
        ;

CLIENT  : TEXT '!' TEXT '@' TEXT
        ;

SPECIAL : '-'
        | '*'
        | '.'
        | ','
        | '#'
        | '_'
        | '?'
        | '!'
        | '@'
        ;

CHAR    : [a-z]
        | [A-Z]
        ;

INTEGER : [0-9]+
        ;

DELIMITER : ':'
          ;