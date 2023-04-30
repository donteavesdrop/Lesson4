package ru.mirea.zakharova.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.zakharova.thread.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private	int	counter	=	0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        TextView TextView = findViewById(R.id.textView2);
        TextView CountTV = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        TextView.setText("\nИмя текущего потока: " + mainThread.getName());
        mainThread.setName("\nМОЙ НОМЕР ГРУППЫ: 7, " +
                "\nНОМЕР ПО СПИСКУ: 10," +
                "\nМОЙ ЛЮБИИМЫЙ ФИЛЬМ: <Исцеление> Киеси Куросава ");
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));
        binding.button.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public void onClick(View view)	{
                long endTime	=	System.currentTimeMillis()	+	20	*	10;
                while	(System.currentTimeMillis()	<	endTime)	{
                    synchronized	(this)	{
                        try	{
                            wait(endTime	- System.currentTimeMillis());
                            Float count1 = Float.parseFloat(binding.editText.getText().toString());
                            Float count2 = Float.parseFloat(binding.editText2.getText().toString());
                            CountTV.setText("Среднее количсетво пар в день "+count1/count2 );
                        }	catch	(Exception	e)	{
                            throw	new	RuntimeException(e);
                        }
                    }
                }
            }
        });
        binding.button2.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{
                new	Thread(new	Runnable()	{
                    public	void run()	{
                        int	numberThread	=	counter++;
                        Log.d("ThreadProject",	String.format("Запущен	поток	№%d	студенткой	группы	%s	номер	по списку	№%d	",	numberThread, "БСБО-07-21",	10));
                        long	endTime	=	System.currentTimeMillis()	+	20	*	1000;
                        while	(System.currentTimeMillis()	<	endTime)	{
                            synchronized	(this)	{
                                try	{
                                    wait(endTime	- System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(),	"Endtime:	"	+	endTime);
                                }	catch	(Exception	e)	{
                                    throw	new	RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject",	"Выполнен поток №	"	+	numberThread);
                        }
                    }
                }).start();
            }

        });
        binding.textView2.append("\nНовое имя потока: " + mainThread.getName());
    }
}