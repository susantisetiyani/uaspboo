package dbcrud;

import dbconnect.MySQLConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Naubyra
 */
public abstract class CRUD<MySQLConnection> {
    public abstract void insert(MySQLConnection m);
    public abstract void delete(MySQLConnection m);
    public abstract void update(MySQLConnection m);
    public abstract void select(MySQLConnection m);
}
