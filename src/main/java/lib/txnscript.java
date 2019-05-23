// for more information, please visit : http://prodageo.insa-rouen.fr/wiki/pmwiki.php?n=FilRouge.CoderTransactionScript

package lib ;

import java.util.Date;
// import libinsa.txnscriptUtil ;

// types retournés par les opérations JDBC
import java.sql.ResultSet;

// pour simuler la presence d'un SGBD et de la base (mock) 
import commondb.mock.CSVLineSplitter ;
import commondb.mock.MockResultSet ;
import java.io.StringReader;

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
	 /*
	String mockSelect =
     "\"id\",\"parent_id\",\"creation_date\",\"name\",\"balance\"\r"
	+"8,,2000-03-01,\"John McHidden\",310\r"
	+"9,,2005-04-01,\"Jane McFound\",-256\r"
	+"10,,2007-10-01,\"von Hidden, Stuart\",180\r" ;
	 */
	 
	// on suppose que la base contient 3 reservations
	String mockHeader = "\"id\",\"parent_id\",\"creation_date\",\"name\",\"balance\"" ;
	String mockLine1  = "8,,2000-03-01,\"John McHidden\",310" ;
	String mockLine2  = "9,,2005-04-01,\"Jane McFound\",-256" ;
	String mockLine3  = "10,,2007-10-01,\"von Hidden, Stuart\",180" ;
	 
	 
   	ResultSet rs = new MockResultSet( mockHeader , mockLine1 , mockLine2 ,  mockLine3 ); 

	logger.info( "remonterEnrReservation : rs initialized" ) ;
	while ( rs.next() )
	{
	 double balance = rs.getDouble ( "balance" ) ;
	 logger.info( "remonterEnrReservation balance : " + balance);
	}

	return rs ;
  }

  
}
