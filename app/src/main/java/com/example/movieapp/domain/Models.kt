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

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Movie(
    @PrimaryKey
    val id: Int = -1,
    val title: String = "",
    val releaseDate: String = "",
    val posterPath: String = "",
    val rating: Double = 0.0,
    val overview: String = ""
): Parcelable

data class MovieListResult(
    val data: LiveData<PagedList<Movie>>,
    val networkErrors: LiveData<String>
)
