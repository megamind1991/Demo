/**
 * 
 */
package com.me.ga.entity;

/**
 * @author GLAD.Author	
 * @version $Id$
 * @since JDK5.0
 */
public class ProcessResult {

    private Boolean result;

    private String msg;

    public ProcessResult() {
    }

    /**
     * @param result
     * @param msg
     */
    public ProcessResult(Boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    /**
     * result is returned.
     * <br>
     * @return  result
     */
    public Boolean getResult() {
        return result;
    }

    /**
     * result is set up.
     * <br>
     * @param result Boolean
     */
    public void isResult(Boolean result) {
        this.result = result;
    }

    /**
     * msg is returned.
     * <br>
     * @return  msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * msg is set up.
     * <br>
     * @param msg String
     */
    public void setMsg(String msg) {
        if (msg == null || msg == "") {
            msg = " ";
        }
        this.msg = msg;
    }

}
