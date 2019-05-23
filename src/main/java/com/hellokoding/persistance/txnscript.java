// for more information, please visit : http://prodageo.insa-rouen.fr/wiki/pmwiki.php?n=FilRouge.CoderTransactionScript

package com.hellokoding.persistance ;

import java.util.Date;
// import libinsa.txnscriptUtil ;

// types retournés par les opérations JDBC
import java.sql.ResultSet;

// pour simuler la presence d'un SGBD et de la base (mock) 
import commondb.mock.CSVLineSplitter ;
import commondb.mock.MockResultSet ;
import java.io.StringReader;

// http://www.avajava.com/tutorials/lessons/how-do-i-escape-a-string-for-java.html
// import org.apache.commons.io.FileUtils;
// import org.apache.commons.lang.StringEscapeUtils;

// pour journaliser
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

public class txnscript {

	int compteurAssocies = 0 ;
	int compteurReservations = 0 ;
	Logger logger = LoggerFactory.getLogger(txnscript.class);
	
 // Pour la syntaxe et la sémantique des noms des opérations, voir
 // http://prodageo.insa-rouen.fr/wiki/pmwiki.php?n=FilRouge.CoderTransactionScript

 // des exemples de fonctions attendus dans de ce fichier sont fournis dans
 // http://prodageo.insa-rouen.fr/wiki/pmwiki.php?n=Umlp.EXU9912txnscript
 
  
 /** 
     * 
     * cre'ation dans l'outil de persistence d'un enregistrement associe
	 * suite à la re'ception d'une demande par le controleur.
     *
     * @param email_address    adresse de messagerie de l'associe qui effectue la réservation (ie : solange.smith@gmail.com)
     * @param name  nom de la personne (ie : 23/07/2019)
     * @return id attributé par le SGBD
     */

  public int creerAssocie ( String email_address, String name ) 
  {
	compteurAssocies = compteurAssocies + 1 ;
    return compteurAssocies ;
  }
	
     /** 
     * 
     * cre'ation dans l'outil de persistence d'un enregistrement de re'servation
	 * suite à la re'ception d'une demande par le controleur.
     *
     * @param id_associe    adresse de messagerie de l'associe qui effectue la réservation (ie : solange.smith@gmail.com)
     * @param date_arrivee  date d'arrivée des amis de l'associe' dans la maison (ie : 23/07/2019)
     * @return id attributé par le SGBD
     */

  public int creerEnrReservation ( String id_associe, Date date_arrivee ) 
  {
	compteurReservations = compteurReservations + 1 ;
    return compteurReservations ;
  }

  public ResultSet remonterEnrReservation ( String emailAddress ) throws Exception
  {
   	ResultSet rs = null ; 
	return rs ;
  }


  public ResultSet remonterEnrAssocies ( ) throws Exception
  {
	 /*
	String mockSelect =
     "\"id\",\"birthdate\",\"firstname\",\"surname\",\"email_address\"\r"
	+"18,2000-03-01,\"John McHidden\",310\r"
	+"19,2005-04-01,\"Jane McFound\",-256\r"
	+"20,2007-10-01,\"von Hidden, Stuart\",180\r" ;
	 */
	 
	// on suppose que la base contient 3 reservations
	String mockHeader =  "\"id\",\"birthdate\",\"firstname\",\"surname\",\"email_address\"\r" ;
	String mockLine1  = "18,2000-03-01,\"Mickey\",\"DISNEY\",\"mickey@example.com\"" ;
	String mockLine2  = "19,2000-05-06,\"Minnie\",\"DISNEY\",\"minnie@example.com\"" ;
	String mockLine3  = "20,2010-12-21,\"Junior\",\"DISNEY\",\"junior@example.com\"" ;
	
	// exemple d'utilisation a faire dans HelloController.java
   	ResultSet rsTestLocal = new MockResultSet( mockHeader , mockLine1 , mockLine2 ,  mockLine3 ); 

	logger.info( "remonterEnrReservation : rs initialized" ) ;
	while ( rsTestLocal.next() )
	{
	 String email_address = rsTestLocal.getString ( "email_address" ) ;
	 logger.info( "remonterEnrReservation email_address : " + email_address);
	}
	rsTestLocal.close() ;

   	ResultSet rs = new MockResultSet( mockHeader , mockLine1 , mockLine2 ,  mockLine3 ); 
	return rs ;
  }



  
}
