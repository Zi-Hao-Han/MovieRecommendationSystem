package me.hzh.movie.utils;
import org.python.core.Py;
import org.python.core.PyException;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
public class JythonAPI {
	public void passPara2Python(String sourcedata){
		PythonInterpreter interpreter = new PythonInterpreter();
        String fileUrlPath ="D:/Project";
        String scriptName ="setdata";
        String funcName ="passdata";
        interpreter.exec("import sys\n" + "import os \n" + "sys.path.append('" +fileUrlPath + "')\n"+ "from "+scriptName+" import *\n");
        PyObject someFunc =interpreter.get(funcName);
       try {
            someFunc.__call__(new PyString(sourcedata));
            System.out.print(sourcedata);
        } catch (PyException e) {
            e.printStackTrace();
        }
		return;
	}
	public String getResultFromPython(){
	    String resultkey = "result";
		PythonInterpreter interpreter = new PythonInterpreter();
        String fileUrlPath ="D:/Project";
        String scriptName ="getdata";
        interpreter.exec("import sys\n" + "import os \n" + "sys.path.append('" +fileUrlPath + "')\n"+ "from "+scriptName+" import *\n");
        return interpreter.get(resultkey).toString();
	}
}




 
