from datetime import datetime
from flask import Flask, Blueprint
import sqlite3

def __init__():
    con = sqlite3.connect('DOSG26.db',check_same_thread=False)
    cur = con.cursor()

##################################################################
# ----------------- CREATE TABLES FOR DATABASE ----------------- #
#
# ----------------- COSTUMERS -----------------------------------#
    cur.execute("""DROP TABLE IF EXISTS costumers""")
    cur.execute(
        """CREATE TABLE costumers (
        user_id     INTEGER                         PRIMARY KEY     ,
        fname       VARCHAR(32)     NOT NULL                        ,
        lname       VARCHAR(32)     NOT NULL                        ,
        email       VARCHAR(64)     NOT NULL        UNIQUE          ,
        nick        VARCHAR(32)     NOT NULL        UNIQUE          ,
        passwd      VARCHAR(64)     NOT NULL                        ); 
        """
        )
    #CREATE TABLE "USERS" (
	#"ID"	INTEGER NOT NULL UNIQUE,
	#"FNAME"	TEXT,
	#"LNAME"	TEXT,
	#"EMAIL"	TEXT NOT NULL UNIQUE,
	#"PASSWORD"	TEXT NOT NULL,
	#"USERNAME"	INTEGER NOT NULL UNIQUE,
	#PRIMARY KEY("ID" AUTOINCREMENT)
#);
    
# ----------------- CART ----------------------------------------#
    cur.execute("""DROP TABLE IF EXISTS cart""")
    cur.execute(
        """CREATE TABLE cart (
        cart_id     INTEGER                         PRIMARY KEY                                             ,  
        user_id     INTEGER                         FOREIGN KEY     REFERENCES DOSG26.costumers(user_id)    ,
        list        VARCHAR(256)                                                                            ,
        total_cost  DECIMAL         NOT NULL                                                                ,
        flag        BINARY          NOT NULL                                                                );                                          
        """
        )
    #CREATE TABLE "CART" (
	#"ID"	INTEGER NOT NULL UNIQUE,
	#"USEDID"	INTEGER NOT NULL UNIQUE,
	#"ITEM0"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM1"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM2"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM3"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM4"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM5"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM6"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM7"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM8"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM9"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM10"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM11"	INTEGER NOT NULL DEFAULT 0,
	#PRIMARY KEY("ID" AUTOINCREMENT)
#);
    
# ----------------- SAFE CART -----------------------------------#
    cur.execute("""DROP TABLE IF EXISTS safecart""")
    cur.execute(
        """CREATE TABLE safecart (
        safe_id     INTEGER                         PRIMARY KEY                                            ,  
        cart_id     INTEGER                         FOREIGN KEY     REFERENCES DOSG26.cart(cart_id)        ,
        list        VARCHAR(256)                    FOREIGN KEY     REFERENCES DOSG26.cart(list)           ,                                                                    ,
        total_cost  DECIMAL         NOT NULL        FOREIGN KEY     REFERENCES DOSG26.cart(total_cost)     ,
        flag        BINARY          NOT NULL        FOREIGN KEY     REFERENCES DOSG26.cart(flag)           );
        """
        )
    
    #CREATE TABLE "WISHLIST" (
	#"ID"	INTEGER NOT NULL UNIQUE,
	#"USEDID"	INTEGER NOT NULL UNIQUE,
	#"ITEM0"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM1"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM2"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM3"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM4"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM5"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM6"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM7"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM8"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM9"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM10"	INTEGER NOT NULL DEFAULT 0,
	#"ITEM11"	INTEGER NOT NULL DEFAULT 0,
	#PRIMARY KEY("ID" AUTOINCREMENT)
#);
    

# ----------------- PRODUCTS -----------------------------------#
    cur.execute("""DROP TABLE IF EXISTS products""")
    cur.execute(
        """CREATE TABLE products (
        product_id  INTEGER                         PRIMARY KEY ,
        descrip     VARCHAR(128)                                ,
        price       DECIMAL                                     );
        """
        ) # Price ---> NOT NULL (Exploit)
    
#
#
#CREATE TABLE "ITEMS" (
#	"ID"	INTEGER NOT NULL UNIQUE,
#	"NAME"	TEXT NOT NULL DEFAULT 'Item',
#	"DESCRIPTION"	TEXT,
#	"CATEGORY"	TEXT NOT NULL DEFAULT 'Others',
#	"IMAGES"	TEXT,
#	"PRICE"	REAL NOT NULL DEFAULT 0,
#	"STOCK"	INTEGER NOT NULL DEFAULT 0,
#	PRIMARY KEY("ID" AUTOINCREMENT)
#);
# ----------------- END: TABLES FOR DATABASE ----------------- #
##################################################################


def create_app():
    app = Flask(__name__)
    __init__()
    return app

apprun = create_app()
if __name__ == "__main__":
        apprun.run(debug=True)