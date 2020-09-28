package com.javaassist;

/**
 * 通过javaassist需要生成的类
 */
public class Emp
{
    private int empno;
    private String ename;

    public Emp()
    {
        super();
    }

    public Emp(int empno, String ename)
    {
        this.empno = empno;
        this.ename = ename;
    }

    public int getEmpno()
    {
        return empno;
    }

    public void setEmpno(int empno)
    {
        this.empno = empno;
    }

    public String getEname()
    {
        return ename;
    }

    public void setEname(String ename)
    {
        this.ename = ename;
    }
}
