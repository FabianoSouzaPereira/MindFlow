package com.fabianospdev.mindflow.features.profile.data.datasources

import com.fabianospdev.mindflow.features.profile.data.model.ProfileFirestoreModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity

abstract class ProfileDataSource {
    abstract suspend fun getProfileContent(): ProfileFirestoreModel

    open suspend fun setProfileContent(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        throw NotImplementedError("setProfileContent(model: ProfileRelationalModel) not implemented")
    }

    open suspend fun setPreferences(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        throw NotImplementedError("setProfileContent(model: ProfileRelationalModel) not implemented")
    }

    open suspend fun setSocialLinks(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        throw NotImplementedError("setProfileContent(model: ProfileFirestoreModel)not implemented")
    }

    open suspend fun updateSocialLinks(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        throw NotImplementedError("setProfileContent(model: ProfileFirestoreModel)not implemented")
    }

    open suspend fun updatePreferences(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        throw NotImplementedError("setProfileContent(model: ProfileFirestoreModel)not implemented")
    }
}