package com.getposted.model;

public interface PurchaseDAO extends DAO<Purchase> {
    /*
     * all purchases count // SELECT COUNT(id) AS count FROM Purchase WHERE publisherId = 1
     * sum of all soft copy purchase prices // SELECT SUM(price) AS sum FROM Purchase WHERE publisherId = 1
     * total soft copy purchases count // SELECT COUNT(id) AS count FROM Purchase WHERE publisherId = 1
     * total users purchased soft copies ( can get the percentage how many users purchases publications from who logged in ) // SELECT COUNT(userId) AS count FROM (SELECT userId FROM Purchase WHERE publisherId = 1 GROUP BY userId) AS table1
     * 
     * 
     * get all pruchases // SELECT * FROM Purchase WHERE publisherId = 1 ORDER BY purchasedDate DESC, purchasedTime DESC
     * get all purchases filter by price asc,desc // SELECT * FROM Purchase WHERE publisherId = 1 ORDER BY price ASC
     * get all purchases on a specific date  // SELECT * FROM Purchase WHERE publisherId = 1 AND purchasedDate = "2024-04-01" ORDER BY purchasedTime DESC
     * get list of purchases on a specific date // SELECT * FROM Purchase WHERE publisherId = 1 AND purchasedDate = "2024-04-01" ORDER BY purchasedTime DESC LIMIT 1
     * get all purchases between date range // SELECT * FROM Purchase WHERE publisherId = 1 AND purchasedDate BETWEEN "2022-01-01" AND "2025-04-01"
     * get all purchases between price range // SELECT * FROM Purchase WHERE publisherId = 1 AND price BETWEEN 0 AND 20
     * 
     * get all purchases of a give user id // SELECT * FROM Purchase WHERE publisherId = 1 AND userId = 1
     * count of all purchases of a user id // SELECT COUNT(id) AS count FROM Purchase WHERE publisherId = 1 AND userId = 1
     * get all purchases of a publication id // SELECT * FROM Purchase WHERE publisherId = 1 AND publicationId = 1
     * count of all purchases of a publication id // SELECT COUNT(id) AS count FROM Purchase WHERE publisherId = 1 AND publicationId = 1
     * total payement of a user id  // SELECT SUM(price) AS sum FROM Purchase WHERE publisherId = 1 AND userId = 1
     * total payment for a publication id // SELECT SUM(price) AS sum FROM Purchase WHERE publisherId = 1 AND publicationId = 1
     * 
     * list of publication ids purchased by user id // SELECT publicationId FROM Purchase WHERE publisherId = 1 AND userId = 1
     * list of user ids who purchased a specific publication id // SELECT userId FROM Purchase WHERE publisherId = 1 AND publicationId = 1
     * 
     * get all of most purchased publications ids asc, desc // SELECT publicationId FROM (SELECT COUNT(id) AS counts,publicationId FROM Purchase GROUP BY publicationId ORDER BY counts DESC) AS table1
     * get list of most purchased publications ids asc, desc // SELECT publicationId FROM (SELECT COUNT(id) AS counts,publicationId FROM Purchase GROUP BY publicationId ORDER BY counts DESC) AS table1 LIMIT 1
     * 
     * 
     * total discount given to users by all authors // SELECT SUM(PU.softCopyDiscount) AS sum FROM Purchase AS P LEFT JOIN Publication AS PU ON P.publicationId = PU.id WHERE publisherId = 1
     * total discount given to users by an author // SELECT SUM(PU.softCopyDiscount) AS sum FROM Purchase AS P LEFT JOIN Publication AS PU ON P.publicationId = PU.id WHERE publisherId = 1 AND authorId = 1
     * total discount got by an user  // SELECT SUM(PU.softCopyDiscount) AS sum FROM Purchase AS P LEFT JOIN Publication AS PU ON P.publicationId = PU.id WHERE publisherId = 1 AND userId = 1
     * 
     * total soft copy commission // SELECT SUM(softCopyCommission) AS sum FROM Purchase AS PU LEFT JOIN Publisher  AS P ON PU.publisherId = P.id WHERE PU.publisherId = 1
     * total soft copy commission from a publication id // SELECT SUM(softCopyCommission) AS sum FROM Purchase AS PU LEFT JOIN Publisher  AS P ON PU.publisherId = P.id WHERE PU.publicationId = 1 AND publisherId = 1
     * total soft copy commission from a user id // SELECT SUM(softCopyCommission) AS sum FROM Purchase AS PU LEFT JOIN Publisher  AS P ON PU.publisherId = P.id WHERE publisherId =1 AND userId = 1
     * total soft copy commission from an author id // SELECT SUM(softCopyCommission) AS sum FROM Purchase AS PU LEFT JOIN Publisher  AS P ON PU.publisherId LEFT JOIN Publication AS Pb ON PU.publicationId = Pb.id LEFT JOIN Author AS A ON A.id = Pb.authorId = P.id WHERE publisherId = 1 AND authorId =1
     * 
     * 
     * Total price income from all authors // SELECT SUM(softCopyPrice) AS sum FROM Purchase AS P LEFT JOIN Publication AS PU ON PU.id = P.publicationId
     * total soft copy price income for author id // SELECT SUM(softCopyPrice) AS sum FROM Purchase AS P LEFT JOIN Publication AS PU ON P.publicationId = PU.id LEFT JOIN Author AS A ON A.id = PU.authorId WHERE PU.authorId = 1 AND publisherId = 1
     * total soft copy price income from a publication id // SELECT softCopyPrice FROM Purchase AS P LEFT JOIN Publication AS PU ON PU.id = P.publicationId WHERE PublisherId = 1 AND PublicationId =1
     * 
     * All authors ordered by top selling // SELECT authorId FROM (SELECT COUNT(PU.authorId) AS authorCount,PU.authorId FROM Purchase AS P LEFT JOIN Publication AS PU ON PU.id = P.publicationId LEFT JOIN Author AS A ON A.id = PU.authorId WHERE publisherId = 1 GROUP BY PU.authorId ORDER BY authorCount DESC) AS table1
     * List of authors ordered by top selling // SELECT authorId FROM (SELECT COUNT(PU.authorId) AS authorCount,PU.authorId FROM Purchase AS P LEFT JOIN Publication AS PU ON PU.id = P.publicationId LEFT JOIN Author AS A ON A.id = PU.authorId WHERE publisherId = 1 GROUP BY PU.authorId ORDER BY authorCount DESC) AS table1 LIMIT 1
     * 
     */
}
