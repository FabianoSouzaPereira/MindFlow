package com.fabianospdev.mindflow.features.user_profile.data.datasources

import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileFirestoreModel
import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileRelationalModel
import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileResponseEntity

abstract class ProfileDataSource {
    abstract suspend fun getProfileContent(): ProfileFirestoreModel

    open suspend fun setProfileContent(model: ProfileRelationalModel): ProfileResponseEntity {
        throw NotImplementedError("setProfileContent(model: ProfileRelationalModel) not implemented")
    }

    open suspend fun setProfileContent(model: ProfileFirestoreModel): ProfileResponseEntity {
        throw NotImplementedError("setProfileContent(model: ProfileFirestoreModel)not implemented")
    }
}