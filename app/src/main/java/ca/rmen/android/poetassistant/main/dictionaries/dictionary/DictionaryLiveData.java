/*
 * Copyright (c) 2016-2017 Carmen Alvarez
 *
 * This file is part of Poet Assistant.
 *
 * Poet Assistant is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Poet Assistant is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Poet Assistant.  If not, see <http://www.gnu.org/licenses/>.
 */

package ca.rmen.android.poetassistant.main.dictionaries.dictionary;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ca.rmen.android.poetassistant.Constants;
import ca.rmen.android.poetassistant.dagger.DaggerHelper;
import ca.rmen.android.poetassistant.main.dictionaries.ResultListData;
import ca.rmen.android.poetassistant.main.dictionaries.ResultListLiveData;

public class DictionaryLiveData extends ResultListLiveData<ResultListData<DictionaryEntry.DictionaryEntryDetails>> {

    private static final String TAG = Constants.TAG + DictionaryLiveData.class.getSimpleName();

    private final String mQuery;
    @Inject Dictionary mDictionary;

    public DictionaryLiveData(Context context, String query) {
        super(context);
        DaggerHelper.getMainScreenComponent(context).inject(this);
        mQuery = query;
    }

    @Override
    public ResultListData<DictionaryEntry.DictionaryEntryDetails> loadInBackground() {
        Log.d(TAG, "loadInBackground() called with: " + "");
        List<DictionaryEntry.DictionaryEntryDetails> result = new ArrayList<>();
        if (TextUtils.isEmpty(mQuery)) return new ResultListData<>(mQuery, result);
        DictionaryEntry entry = mDictionary.lookup(mQuery);
        Collections.addAll(result, entry.details);
        return new ResultListData<>(entry.word, result);
    }

}
