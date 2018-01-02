package ru.job4j.sql.jdbc;

import java.util.List;

/**
 * Class implements work with DB and XML.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 30.12.2017.
 */
public class XmlJdbcOptimization {

    private String dbUrl = "jdbc:sqlite:d:/java/abondarev/chapter_008/src/resources/entries.db";

    private String login;

    private String password;

    private static String firstXml = "D:\\java\\abondarev\\chapter_008\\1.xml";
    private static String secondXml = "D:\\java\\abondarev\\chapter_008\\2.xml";

    private int n = 1_000_000;

    public void setN(int n) {
        this.n = n;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Start executing work.
     *
     * @return work time.
     */
    public long execute() {
        DBService service = new DBService(dbUrl, login, password);
        long time = - System.currentTimeMillis();
        service.createTable();
        service.fillTable(n);
        List<Entry> entries = service.getAll();

        XMLService xmlService = new XMLService();

        xmlService.createDOM(entries, firstXml);

        xmlService.domToXSLT(firstXml, secondXml);

        System.out.println(xmlService.sumOfAttributeValues(secondXml, "field"));

        time += System.currentTimeMillis();
        return time;
    }
}
