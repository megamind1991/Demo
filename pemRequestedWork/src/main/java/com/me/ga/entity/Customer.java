/**
 * 
 */
package com.me.ga.entity;


/**
 * 
 * @author GLAD.Author	
 * @version $Id$
 * @since JDK5.0
 */
public class Customer {

    private int id;

    private String name;

    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * {@inheritDoc}
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}
