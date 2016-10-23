package com.vincent.tatiana.homeworktrack;

/**
 * Created by tatiana on 10/23/16.
 */

import android.content.res.Resources;

public class NumberGenerator {
    private static final int TEN = 10;
    private static final int TWENTY = 20;
    private static final int HUNDRED = 100;
    private static final int THOUSAND = 1000;
    private static final int MILLION = 1000000;


    public static String getHundreds(int i, boolean male, Resources res) {
        StringBuilder number = new StringBuilder();
        switch ((i % THOUSAND) / HUNDRED) {
            case 1: number.append(res.getString(R.string.number_100)); break;
            case 2: number.append(res.getString(R.string.number_200)); break;
            case 3: number.append(res.getString(R.string.number_300)); break;
            case 4: number.append(res.getString(R.string.number_400)); break;
            case 5: number.append(res.getString(R.string.number_500)); break;
            case 6: number.append(res.getString(R.string.number_600)); break;
            case 7: number.append(res.getString(R.string.number_700)); break;
            case 8: number.append(res.getString(R.string.number_800)); break;
            case 9: number.append(res.getString(R.string.number_900)); break;
        }

        if (i >= HUNDRED && i % HUNDRED != 0) {
            number.append(" ");
        }

        if (i % HUNDRED >= TEN && i % HUNDRED < TWENTY) {
            switch (i % HUNDRED) {
                case 10: number.append(res.getString(R.string.number_10)); break;
                case 11: number.append(res.getString(R.string.number_11)); break;
                case 12: number.append(res.getString(R.string.number_12)); break;
                case 13: number.append(res.getString(R.string.number_13)); break;
                case 14: number.append(res.getString(R.string.number_14)); break;
                case 15: number.append(res.getString(R.string.number_15)); break;
                case 16: number.append(res.getString(R.string.number_16)); break;
                case 17: number.append(res.getString(R.string.number_17)); break;
                case 18: number.append(res.getString(R.string.number_18)); break;
                case 19: number.append(res.getString(R.string.number_19)); break;
            }
        } else {
            switch ((i % HUNDRED) / TEN) {
                case 2: number.append(res.getString(R.string.number_20)); break;
                case 3: number.append(res.getString(R.string.number_30)); break;
                case 4: number.append(res.getString(R.string.number_40)); break;
                case 5: number.append(res.getString(R.string.number_50)); break;
                case 6: number.append(res.getString(R.string.number_60)); break;
                case 7: number.append(res.getString(R.string.number_70)); break;
                case 8: number.append(res.getString(R.string.number_80)); break;
                case 9: number.append(res.getString(R.string.number_90)); break;
            }

            if (i % TEN != 0 && i >= TEN) {
                number.append(" ");
            }

            switch (i % TEN) {
                case 1: if (male) number.append(res.getString(R.string.number_1_male));
                else number.append(res.getString(R.string.number_1_female)); break;
                case 2: if (male) number.append(res.getString(R.string.number_2_male));
                else number.append(res.getString(R.string.number_2_female)); break;
                case 3: number.append(res.getString(R.string.number_3)); break;
                case 4: number.append(res.getString(R.string.number_4)); break;
                case 5: number.append(res.getString(R.string.number_5)); break;
                case 6: number.append(res.getString(R.string.number_6)); break;
                case 7: number.append(res.getString(R.string.number_7)); break;
                case 8: number.append(res.getString(R.string.number_8)); break;
                case 9: number.append(res.getString(R.string.number_9)); break;
            }
        }

        return number.toString();
    }

    public static String getThousand(int i, Resources res) {
        StringBuilder number = new StringBuilder();

        if ((i % MILLION) >= THOUSAND) {
            number.append(getHundreds((i % MILLION) / THOUSAND, false, res));
            number.append(" ");

            int j = i / THOUSAND;
            if (j % HUNDRED >= 5 && j % HUNDRED <= TWENTY) {
                number.append(res.getString(R.string.number_1000_plural));
            } else if (j % 10 == 1) {
                number.append(res.getString(R.string.number_1000_single));
            } else if (j % 10 >= 2 && j % 10 <= 4) {
                number.append(res.getString(R.string.number_1000_several));
            } else {
                number.append(res.getString(R.string.number_1000_plural));
            }
        }

        return number.toString();
    }

    public static String getMillion(int i, Resources res) {
        StringBuilder number = new StringBuilder();

        if (i >= MILLION) {
            number.append(getHundreds(i / MILLION, true, res));
            number.append(" ");

            int j = i / MILLION;
            if (j % HUNDRED >= 5 && j % HUNDRED <= TWENTY) {
                number.append(res.getString(R.string.number_million_plural));
            } else if (j % TEN == 1) {
                number.append(res.getString(R.string.number_million_single));
            } else if (j % TEN >= 2 && j % TEN <= 4) {
                number.append(res.getString(R.string.number_million_several));
            } else {
                number.append(res.getString(R.string.number_million_plural));
            }
        }

        return number.toString();
    }

    public static String getNumber(int i, Resources res) {
        if (i >= THOUSAND * MILLION) {
            return "";
        }

        StringBuilder number = new StringBuilder();

        number.append(getMillion(i, res));

        if (i >= MILLION && i % MILLION != 0) {
            number.append(" ");
        }

        number.append(getThousand(i, res));

        if ((i % MILLION) >= THOUSAND && i % THOUSAND != 0) {
            number.append(" ");
        }

        if (i % THOUSAND != 0) {
            number.append(getHundreds(i % THOUSAND, true, res));
        }

        return number.toString();
    }
}
