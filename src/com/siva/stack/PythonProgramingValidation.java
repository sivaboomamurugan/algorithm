package com.siva.stack;

/**
 * Created by sivam on 3/15/17.
 */
public class PythonProgramingValidation {

    /*// Validation rules:
// 1. The first line cannot be indented
// 2. Control statements end with colon
// 3. Control statements must be followed by a line indented at least one space more than the control statement
// 4. Lines after non-control statements must have an indent equal to either the previous line or the indentation level associated with some enclosing block

// Sample Program
*//* 0 *//*print "begin"
*//* 0 *//*for i in range(0, 10):
*//* 6 *//*      if i % 2 == 0:
*//* 12 *//*            print "even"
*//* 6 *//*      else:
*//* 8 *//*        print "odd"
*//* 8 *//*        print "really!"
    print "really!"


    int validatePythonPrgm(ArrayList<String> list) {

        if(null == list || list.size == 0)
            return -1;

        boolean isCurrentBlockConditional = false;
        Stack stack = new Stack();
        stack.push(0);

        if(list.get(0).startsWith(" "))
            return "1";

        for(int index=0; index < list.size; index++) {
            String currentLine = list.get(index);

            if(currentLine.endsWith(":"))
                isCurrentBlockConditional = true;
            int lastWhiteSpaceIndex = currentLine.lastIndexOf(" ");
            if(!isCurrentBlockConditional && stack.peek() > lastWhiteSpaceIndex)
                return ++index;
            else if(isCurrentBlockConditional && stack.peek() < lastWhiteSpaceIndex)
                return ++index;
            else if(stack.peek() != lastWhiteSpaceIndex) {
                isCurrentBlockConditional = false;
                stack.pop();
            }
            if(stack.peek() != lastWhiteSpaceIndex) {
                stack.push(lastWhiteSpaceIndex);

            }


        }

        return -1;

    }*/


}
