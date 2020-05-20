module DaoProject {
    requires ModelProject;
    requires java.sql;
    exports dao;
    exports dao.file;
    exports dao.jdbc;
    exports dao.exceptions.file;
    exports dao.exceptions.jdbc;
    opens dao.exceptions;
    exports dao.exceptions;
}