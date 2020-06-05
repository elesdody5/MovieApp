/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.devbyteviewer.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.movieapp.data.source.local_data.entity.LocalMovie


/**
 * Domain objects are plain Kotlin data classes that represent the things in our app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 *
 * @see database for objects that are mapped to the database
 * @see network for objects that parse or prepare network calls
 */


data class Movie(
    val title: String,
    val overview: String,
    val posterPath: String,
    val rating: Double,
    val releaseDate: String
)
data class MovieListResult(
    val data: LiveData<PagedList<LocalMovie>>,
    val networkErrors: LiveData<String>
)
