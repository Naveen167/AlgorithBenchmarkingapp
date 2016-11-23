package com.felight.algorithbenchmarkingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import static com.felight.algorithbenchmarkingapp.R.id.btnBubble;


public class MainActivity extends AppCompatActivity {

    private RadioGroup radioComplexGroup;
    private RadioButton radioComplexButton;
    private Button btnGen;
    private EditText arrSize;
    private TextView arrRes;
    private LinearLayout linLay;
    private TextView Bubble;
    private TextView Insertion;
    private TextView Selection;
    private TextView Merge;
    private Button benchBubble;
    private Button benchInsertion;
    private Button benchSelection;
    private Button benchMMerge;



    private int copyArraySize;
    int[] temp=new int[copyArraySize];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appbench_layout);
        arrSize = (EditText) findViewById(R.id.etArraySize);
        arrRes = (TextView) findViewById(R.id.tvArrayGenRes);
        linLay = (LinearLayout) findViewById(R.id.linearSecond);
        Bubble = (TextView) findViewById(R.id.tvBubble);
        Insertion = (TextView) findViewById(R.id.tvInsertion);
        Selection = (TextView) findViewById(R.id.tvSelection);
        Merge = (TextView) findViewById(R.id.tvMerge);
        benchBubble = (Button) findViewById(btnBubble);
        benchInsertion = (Button) findViewById(R.id.btnInsertion);
        benchSelection = (Button) findViewById(R.id.btnSelection);
        benchMMerge = (Button) findViewById(R.id.btnMerge);
        addListenerOnButton();
        linLay.setVisibility(View.INVISIBLE);
        ;
    }
    public void doSomething(View view){


        MyTask myTask = new MyTask(this);

        myTask.execute(1000,2000,3000,1000,5000);


    }

    public void addListenerOnButton() {

        radioComplexGroup = (RadioGroup) findViewById(R.id.rBtnGroup);
        btnGen = (Button) findViewById(R.id.btnArrayGenerate);

        btnGen.setOnClickListener(new OnClickListener() {


            public void onClick(View view) {
                int selectedId = radioComplexGroup.getCheckedRadioButtonId();
                radioComplexButton = (RadioButton) findViewById(selectedId);
                Toast.makeText(MainActivity.this, radioComplexButton.getText(), Toast.LENGTH_SHORT).show();
                int sizeNum = 0;

                try {
                    sizeNum = Integer.parseInt(arrSize.getText().toString());
                    copyArraySize=sizeNum;
                } catch (NumberFormatException e) {
                    arrRes.setText("Please Enter the Array Size Correctly");
                    return;
                }

                int[] arr1=new int [sizeNum];

                switch (radioComplexButton.getId()){

                    case R.id.rBtnBest:
                        arrRes.setText("Array Generated, Size : "+sizeNum);
                        for (int i=0;i<sizeNum;i++){
                            arr1[i]=i+1;
                        }
                        temp = arr1.clone();
                        break;
                    case R.id.rBtnAvg:
                        for(int i=0;i<sizeNum;i++){
                            arr1[i]=(int)(Math.random()*100);
                        }
                        arrRes.setText("Array Generated with size : "+sizeNum);
                        temp = arr1.clone();
                        break;
                    case  R.id.rBtnWorst:
                        int j=0;
                        for(int i=sizeNum;i>0;i--){
                            arr1[j++]=i;
                        }
                        arrRes.setText("Array Generated with size : "+sizeNum);
                        temp = arr1.clone();
                        break;
                }

                //arrRes.setVisibility(View.VISIBLE);
                linLay.setVisibility(View.VISIBLE);


            }
        });
    }

    public void perSort(View view){
        int[] temp=new int[copyArraySize];

        switch(view.getId())
        {
            case R.id.btnBubble:
                long start=System.currentTimeMillis();
                SortAlgorithm.bubble(temp);
                long end=System.currentTimeMillis();
                Bubble.setText((end-start)+"ms");
benchBubble.setEnabled(false);
                benchInsertion.setEnabled(false);
                benchSelection.setEnabled(false);
                benchMMerge.setEnabled(false);

                break;

            case R.id.btnInsertion:
                start=System.currentTimeMillis();
                SortAlgorithm.insertion(temp);
                end=System.currentTimeMillis();
                Insertion.setText((end-start)+"ms");
                break;

            case R.id.btnSelection:
                start=System.currentTimeMillis();
                SortAlgorithm.selection(temp);
                end=System.currentTimeMillis();
                Selection.setText((end-start)+"ms");
                break;
            case R.id.btnMerge:
                start=System.currentTimeMillis();
                SortAlgorithm.mergeSort(temp);
                end=System.currentTimeMillis();
                Merge.setText((end-start)+"ms");
                break;
        }
    }

    public void benchAll(View view){
        int[] temp=new int[copyArraySize];

        long start=System.currentTimeMillis();
        SortAlgorithm.bubble(temp);
        long end=System.currentTimeMillis();
        Bubble.setText((end-start)+"ms");

        start=System.currentTimeMillis();
        SortAlgorithm.insertion(temp);
        end=System.currentTimeMillis();
        Insertion.setText((end-start)+"ms");

        start=System.currentTimeMillis();
        SortAlgorithm.selection(temp);
        end=System.currentTimeMillis();
        Selection.setText((end-start)+"ms");

        start=System.currentTimeMillis();
        SortAlgorithm.mergeSort(temp);
        end=System.currentTimeMillis();
        Merge.setText((end-start)+"ms");

        benchBubble.setEnabled(false);
        benchInsertion.setEnabled(false);
        benchSelection.setEnabled(false);
        benchMMerge.setEnabled(false);
    }

}
