package com.example.counter;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_plus;//乘
    private Button button_sub;//减
    private Button button_add;//加
    private Button button_div;//除
    private Button button_spot;//小数点
    private Button button_equal;//等于
    private Button button_back;//返回键
    private Button button_delete_all;//清空
    private Button button_remainder;//求余
    private Button button_powNum;//次方

    private EditText editText;//表达式
    private EditText resultText;//结果

    private Stack<Integer> m_numStack;//数字栈
    private Stack<Character> m_opStack;//符号栈
    private Map<Character,Integer> m_opMap;//重设优先级

    private Boolean isOpFirst =  true;//判断第一个是否为运算符
    private Boolean isOpFinal = false;//判断最后一个字符是否为运算符


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_0 = (Button) findViewById(R.id.button_0);
        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        button_3 = (Button) findViewById(R.id.button_3);
        button_4 = (Button) findViewById(R.id.button_4);
        button_5 = (Button) findViewById(R.id.button_5);
        button_6 = (Button) findViewById(R.id.button_6);
        button_7 = (Button) findViewById(R.id.button_7);
        button_8 = (Button) findViewById(R.id.button_8);
        button_9 = (Button) findViewById(R.id.button_9);
        button_add = (Button) findViewById(R.id.button_add);
        button_sub = (Button) findViewById(R.id.button_sub);
        button_plus = (Button) findViewById(R.id.button_plus);
        button_div = (Button) findViewById(R.id.button_div);
        button_spot = (Button) findViewById(R.id.button_spot);
        button_equal = (Button) findViewById(R.id.button_equal);
        button_back = (Button) findViewById(R.id.button_back);
        button_delete_all = (Button) findViewById(R.id.delete_all);
        button_remainder = (Button) findViewById(R.id.remainder);
        button_powNum = (Button) findViewById(R.id.button_pow);


        editText = (EditText) findViewById(R.id.edit_text);
        resultText = (EditText) findViewById(R.id.result_text);

        m_numStack = new Stack();
        m_opStack = new Stack();

        m_opMap = new HashMap<Character, Integer>();

        m_opMap.put('[',0);
        m_opMap.put(']',0);
        m_opMap.put('+',1);
        m_opMap.put('-',1);
        m_opMap.put('*',2);
        m_opMap.put('/',2);
        m_opMap.put('%',2);
        m_opMap.put('^',2);





        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_sub.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_div.setOnClickListener(this);
        button_spot.setOnClickListener(this);
        button_equal.setOnClickListener(this);
        button_back.setOnClickListener(this);
        button_delete_all.setOnClickListener(this);
        button_remainder.setOnClickListener(this);
        button_powNum.setOnClickListener(this);

    }

    public void onClick(View v) {
        String str = editText.getText().toString();
        switch (v.getId()) {
            case R.id.button_0:

            case R.id.button_1:

            case R.id.button_2:

            case R.id.button_3:

            case R.id.button_4:

            case R.id.button_5:

            case R.id.button_6:

            case R.id.button_7:

            case R.id.button_8:

            case R.id.button_9:{
                editText.setText(str + ((Button) v).getText());
                isOpFirst = false;
                break;
            }

            case R.id.button_add:

            case R.id.button_sub:

            case R.id.button_plus:

            case R.id.remainder:

            case R.id.button_pow:

            case R.id.button_div: {
                if (isOpFirst)return;
                else if(isOpFinal){
                    if (str != null && !str.equals("")) {
                        str = editText.getText().toString();
                        editText.setText(str.substring(0, str.length() - 1) +((Button) v).getText() );
                    }
                    break;
                }
                editText.setText(str + ((Button) v).getText());
                isOpFinal = true;//当前最后一个字符为运算符
                break;
            }

            case R.id.button_spot: {
                editText.setText(str + ((Button) v).getText());
                break;
            }

            case R.id.button_equal: {
                if (isOpFirst)return;
                Expression e = new Expression();
                e.ScanExpression();
                String strTemp = Integer.toString(m_numStack.pop());
                resultText.setText(strTemp);
                break;
            }

            case R.id.button_back: {
                if (str != null && !str.equals("")) {
                    editText.setText(str.substring(0, str.length() - 1));
                    break;
                }
            }
            case R.id.delete_all:{
                editText.setText("");
                resultText.setText("");
            }

            default:
                break;
        }
    }
    class Expression{
        private void ScanExpression() {

            int i = 0;
            int num = 0;
            char ch = ' ';
            boolean isNumInStack = false;

            String expression = editText.getText() + "]";
            m_numStack.push(0);
            m_opStack.push('[');
            while (true) {
                ch = expression.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    num = num * 10 + (ch - '0');
                    isNumInStack = true;
                    i++;
                } else {
                    if (isNumInStack) {
                        m_numStack.push(num);
                        isNumInStack = false;
                        num = 0;
                    }

                    char op = m_opStack.peek();

                    if (m_opMap.get(ch) > m_opMap.get(op)) {
                        m_opStack.push(ch);
                        i++;
                    } else {
                        int rightNum = Integer.valueOf(m_numStack.pop());
                        int leftNum = Integer.valueOf(m_numStack.pop());
                        if (op != '[') {
                            m_opStack.pop();
                        }

                        m_numStack.push(Count(leftNum, rightNum, op));
                    }
                }

                if (ch == ']' && m_opStack.peek() == '[') {
                    break;
                }
            }
        }
        private int Count(int leftNum, int rightNum, char op) {

            switch (op) {
                case '+':
                    return leftNum + rightNum;
                case '-':
                    return leftNum - rightNum;
                case '*':
                    return leftNum * rightNum;
                case '/':
                    if (0 == rightNum) {
                        Toast.makeText(MainActivity.this,"除数不能为0",Toast.LENGTH_SHORT).show();
                        return leftNum;
                    }
                    return leftNum / rightNum;
                case '%':
                    return leftNum % rightNum;
                case '^':
                    return (int)Math.pow(leftNum,rightNum);
                case '[':
                    return rightNum;
            }
            return 0;
        }
    }

}