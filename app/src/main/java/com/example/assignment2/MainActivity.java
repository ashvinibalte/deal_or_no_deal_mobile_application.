package com.example.assignment2;
//a. Assignment #:  Assignment2
//b. File Name: Ashvini Bhanudas Balte
//c. Full name of the student: MainActivity.java
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static List<Integer> numbers = new ArrayList<>();
    public static final int final_tot = 661461;
    int uptodateIndex = 0, tot_count = 4, buttoncal = 0, tot_sum = 0;
    int[] hit_vist = new int[10];

    double offer;
    TextView chooseCaseTextView;
    Button dealButton, noDealButton, resetButton;
    private ImageView imageViewSuitcase1, imageViewSuitcase2, imageViewSuitcase3, imageViewSuitcase4, imageViewSuitcase5, imageViewSuitcase6,
            imageViewSuitcase7, imageViewSuitcase8, imageViewSuitcase9, imageViewSuitcase10,
            reward1ImageView, reward10ImageView, reward50ImageView, reward100ImageView, reward300ImageView, reward1000ImageView,
            reward10000ImageView, reward50000ImageView, reward100000ImageView, reward500000ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseCaseTextView = findViewById(R.id.CHOOSE);

        imageViewSuitcase1 = findViewById(R.id.suitcasePosition1);
        imageViewSuitcase2 = findViewById(R.id.suitcasePosition2);
        imageViewSuitcase3 = findViewById(R.id.suitcasePosition3);
        imageViewSuitcase4 = findViewById(R.id.suitcasePosition4);
        imageViewSuitcase5 = findViewById(R.id.suitcasePosition5);
        imageViewSuitcase6 = findViewById(R.id.suitcasePosition6);
        imageViewSuitcase7 = findViewById(R.id.suitcasePosition7);
        imageViewSuitcase8 = findViewById(R.id.suitcasePosition8);
        imageViewSuitcase9 = findViewById(R.id.suitcasePosition9);
        imageViewSuitcase10 = findViewById(R.id.suitcasePosition10);

        reward1ImageView = findViewById(R.id.reward1);
        reward10ImageView = findViewById(R.id.reward_10);
        reward50ImageView = findViewById(R.id.reward_50);
        reward100ImageView = findViewById(R.id.reward_100);
        reward300ImageView = findViewById(R.id.reward_300);
        reward1000ImageView = findViewById(R.id.reward_1000);
        reward10000ImageView = findViewById(R.id.reward_10000);
        reward50000ImageView = findViewById(R.id.reward_50000);
        reward100000ImageView = findViewById(R.id.reward_100000);
        reward500000ImageView = findViewById(R.id.reward_500000);

        dealButton = findViewById(R.id.Deal);
        noDealButton = findViewById(R.id.NoDeal);
        resetButton = findViewById(R.id.Reset);

        dealButton.setVisibility(View.INVISIBLE);
        noDealButton.setVisibility(View.INVISIBLE);
        chooseCaseTextView.setText("Choose " + tot_count + " cases");

        Arrays.fill(hit_vist, 0);
        initializeNumbersList();

        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseCaseTextView.setText("You Won $" + offer);
                dealButton.setVisibility(View.INVISIBLE);
                noDealButton.setVisibility(View.INVISIBLE);
            }
        });

        noDealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttoncal++;
                if (buttoncal == 1) {
                    tot_count = 4;
                } else if (buttoncal == 2) {
                    tot_count = 1;
                } else {
                    tot_count = 4;
                }
                chooseCaseTextView.setText("Choose " + tot_count + " cases");
                dealButton.setVisibility(View.INVISIBLE);
                noDealButton.setVisibility(View.INVISIBLE);
                resetSuitcaseClick();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetingGameLogic();
            }
        });

        setSuitcaseClickListener(imageViewSuitcase1, hit_vist, 0);
        setSuitcaseClickListener(imageViewSuitcase2, hit_vist, 1);
        setSuitcaseClickListener(imageViewSuitcase3, hit_vist, 2);
        setSuitcaseClickListener(imageViewSuitcase4, hit_vist, 3);
        setSuitcaseClickListener(imageViewSuitcase5, hit_vist, 4);
        setSuitcaseClickListener(imageViewSuitcase6, hit_vist, 5);
        setSuitcaseClickListener(imageViewSuitcase7, hit_vist, 6);
        setSuitcaseClickListener(imageViewSuitcase8, hit_vist, 7);
        setSuitcaseClickListener(imageViewSuitcase9, hit_vist, 8);
        setSuitcaseClickListener(imageViewSuitcase10, hit_vist, 9);
    }

    private void setSuitcaseClickListener(ImageView imageView, int[] hit_vist, int index) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hit_vist[index] == 0) {
                    openSuitcaseLogic(imageView);
                    imageView.setClickable(false);
                }
                hit_vist[index] = 1;
            }
        });
    }

    private static void initializeNumbersList() {
        numbers.clear();
        numbers.add(1);
        numbers.add(10);
        numbers.add(50);
        numbers.add(100);
        numbers.add(300);
        numbers.add(1000);
        numbers.add(10000);
        numbers.add(50000);
        numbers.add(100000);
        numbers.add(500000);
        Collections.shuffle(numbers);
    }

    @SuppressLint("SetTextI18n")
    private void openSuitcaseLogic(ImageView clickedImageView) {
        StringBuilder sb = new StringBuilder();
        if (uptodateIndex < numbers.size()) {
            int amont = numbers.get(uptodateIndex);
            assignValuestoImages(clickedImageView, amont);
            setReward(amont);
            uptodateIndex++;
            tot_sum = tot_sum + amont;
        }

        if (buttoncal == 2 && tot_count == 1) {
            chooseCaseTextView.setText("Congratulations! You won $" + (final_tot - tot_sum));
            dealButton.setVisibility(View.INVISIBLE);
            noDealButton.setVisibility(View.INVISIBLE);
            allSuitcasedisabled();
        } else {
            if (buttoncal < 3 || tot_count > 1) {
                if (tot_count > 1) {
                    --tot_count;
                    if (buttoncal == 2) {
                        chooseCaseTextView.setText("Select 1 case");
                    } else {
                        chooseCaseTextView.setText("Choose " + tot_count + " cases");
                    }
                } else {
                    if (buttoncal == 2) {
                        dealButton.setVisibility(View.INVISIBLE);
                        noDealButton.setVisibility(View.INVISIBLE);
                        sb.append("You won $");
                    } else {
                        dealButton.setVisibility(View.VISIBLE);
                        noDealButton.setVisibility(View.VISIBLE);
                        sb.append("Bank Deal is $");
                    }
                    offer = ((final_tot - tot_sum) / (10 - uptodateIndex)) * (0.6);
                    chooseCaseTextView.setText(sb.toString() + offer);
                }
            }
        }
    }

    private void allSuitcasedisabled() {
        imageViewSuitcase1.setClickable(false);
        imageViewSuitcase2.setClickable(false);
        imageViewSuitcase3.setClickable(false);
        imageViewSuitcase4.setClickable(false);
        imageViewSuitcase5.setClickable(false);
        imageViewSuitcase6.setClickable(false);
        imageViewSuitcase7.setClickable(false);
        imageViewSuitcase8.setClickable(false);
        imageViewSuitcase9.setClickable(false);
        imageViewSuitcase10.setClickable(false);
    }

    private void resetSuitcaseClick() {
        imageViewSuitcase1.setClickable(true);
        imageViewSuitcase2.setClickable(true);
        imageViewSuitcase3.setClickable(true);
        imageViewSuitcase4.setClickable(true);
        imageViewSuitcase5.setClickable(true);
        imageViewSuitcase6.setClickable(true);
        imageViewSuitcase7.setClickable(true);
        imageViewSuitcase8.setClickable(true);
        imageViewSuitcase9.setClickable(true);
        imageViewSuitcase10.setClickable(true);
    }

    private void resetingGameLogic() {
        if (buttoncal < 3) {
            initializeNumbersList();
            uptodateIndex = 0;
            tot_count = 4;
            buttoncal = 0;
            tot_sum = 0;
            offer = 0;
            Arrays.fill(hit_vist, 0);
            chooseCaseTextView.setText("Choose " + tot_count + " cases");
            dealButton.setVisibility(View.INVISIBLE);
            noDealButton.setVisibility(View.INVISIBLE);
            imageViewSuitcase1.setClickable(true);
            imageViewSuitcase2.setClickable(true);
            imageViewSuitcase3.setClickable(true);
            imageViewSuitcase4.setClickable(true);
            imageViewSuitcase5.setClickable(true);
            imageViewSuitcase6.setClickable(true);
            imageViewSuitcase7.setClickable(true);
            imageViewSuitcase8.setClickable(true);
            imageViewSuitcase9.setClickable(true);
            imageViewSuitcase10.setClickable(true);
            resetSuitcaseImages();
            resetRewardImages();
        } else {
            startNewGame();
        }
    }

    private void resetRewardImages() {
        reward1ImageView.setImageResource(R.drawable.reward_1);
        reward10ImageView.setImageResource(R.drawable.reward_10);
        reward50ImageView.setImageResource(R.drawable.reward_50);
        reward100ImageView.setImageResource(R.drawable.reward_100);
        reward300ImageView.setImageResource(R.drawable.reward_300);
        reward1000ImageView.setImageResource(R.drawable.reward_1000);
        reward10000ImageView.setImageResource(R.drawable.reward_100000);
        reward50000ImageView.setImageResource(R.drawable.reward_500000);
        reward100000ImageView.setImageResource(R.drawable.reward_100000);
        reward500000ImageView.setImageResource(R.drawable.reward_500000);
    }

    private void setReward(int amont) {
        switch (amont) {
            case 1:
                reward1ImageView.setImageResource(R.drawable.reward_open_1);
                break;
            case 10:
                reward10ImageView.setImageResource(R.drawable.reward_open_10);
                break;
            case 50:
                reward50ImageView.setImageResource(R.drawable.reward_open_50);
                break;
            case 100:
                reward100ImageView.setImageResource(R.drawable.reward_open_100);
                break;
            case 300:
                reward300ImageView.setImageResource(R.drawable.reward_open_300);
                break;
            case 1000:
                reward1000ImageView.setImageResource(R.drawable.reward_open_1000);
                break;
            case 10000:
                reward10000ImageView.setImageResource(R.drawable.reward_open_10000);
                break;
            case 50000:
                reward50000ImageView.setImageResource(R.drawable.reward_open_50000);
                break;
            case 100000:
                reward100000ImageView.setImageResource(R.drawable.reward_open_100000);
                break;
            case 500000:
                reward500000ImageView.setImageResource(R.drawable.reward_open_500000);
                break;
            default:
                break;
        }
    }

    private void assignValuestoImages(ImageView imageView, int amont) {
        switch (amont) {
            case 1:
                imageView.setImageResource(R.drawable.suitcase_open_1);
                break;
            case 10:
                imageView.setImageResource(R.drawable.suitcase_open_10);
                break;
            case 50:
                imageView.setImageResource(R.drawable.suitcase_open_50);
                break;
            case 100:
                imageView.setImageResource(R.drawable.suitcase_open_100);
                break;
            case 300:
                imageView.setImageResource(R.drawable.suitcase_open_300);
                break;
            case 1000:
                imageView.setImageResource(R.drawable.suitcase_open_1000);
                break;
            case 10000:
                imageView.setImageResource(R.drawable.suitcase_open_10000);
                break;
            case 50000:
                imageView.setImageResource(R.drawable.suitcase_open_50000);
                break;
            case 100000:
                imageView.setImageResource(R.drawable.suitcase_open_100000);
                break;
            case 500000:
                imageView.setImageResource(R.drawable.suitcase_open_500000);
                break;
            default:
                break;
        }
    }

    private void resetSuitcaseImages() {
        imageViewSuitcase1.setImageResource(R.drawable.suitcase_position_1);
        imageViewSuitcase2.setImageResource(R.drawable.suitcase_position_2);
        imageViewSuitcase3.setImageResource(R.drawable.suitcase_position_3);
        imageViewSuitcase4.setImageResource(R.drawable.suitcase_position_4);
        imageViewSuitcase5.setImageResource(R.drawable.suitcase_position_5);
        imageViewSuitcase6.setImageResource(R.drawable.suitcase_position_6);
        imageViewSuitcase7.setImageResource(R.drawable.suitcase_position_7);
        imageViewSuitcase8.setImageResource(R.drawable.suitcase_position_8);
        imageViewSuitcase9.setImageResource(R.drawable.suitcase_position_9);
        imageViewSuitcase10.setImageResource(R.drawable.suitcase_position_10);
    }

    private void startNewGame() {
        resetingGameLogic();
    }
}