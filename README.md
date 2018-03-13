# Open Web's Intro to Android Workshop Spring 2018

## Basic Outline 
1. Start Android Studio, Create new Project
    - Briefly explain the IDE/simulator
2. Go to `activity_main.xml`, explain difference between Text vs. Design view
    - Explain layout (currently uses `RelativeLayout`, we could probably use `ConstraintLayout`)
    - Add EditText for entering the Bill total
    - Add SeekBar for controlling tip amount
      - Set the min at 0, max at 100, and initial progress at 18 (for 18% tip)
        ```
        android:min="0"
        android:progress="18"
        android:max="100"
        ```
    - Add three TextViews
      - One for showing tip percentage as the user moves the SeekBar
      - One for showing the actual tip amount in dollars
      - One for showing the final total 
    - Add a Button for navigating to the SplitActivity
3. Go to `MainActivity.java`
    - Add binding for the components in XML (i.e. EditText, SeekBar, etc)
    - Add double variables for tip, bill total, final total
      ```
      private double billTotal;
      private double tip;
      private double total;
      ```
    - Add event listener for SeekBar
      - `tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {...`
      - Add logic within event handler to update textviews based on tip percentage being changed
    - Add event listener for bill total EditText
      - `totalEditTxt.addTextChangedListener(new TextWatcher() {...`
      - Add logic within event handler to update textviews based on bill total being changed
    - Add event listener for button to navigate to SplitActivity
    
