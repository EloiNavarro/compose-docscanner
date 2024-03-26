package com.eloinavarro.docscanner.injection

import com.eloinavarro.docscanner.data.ScannedDocumentRepository
import com.eloinavarro.docscanner.ui.screens.detail.DetailViewModel
import com.eloinavarro.docscanner.ui.screens.overview.OverviewViewModel
import java.util.UUID

class Provider {
    companion object {
        fun provideOverviewViewModel(): OverviewViewModel {
            return OverviewViewModel(ScannedDocumentRepository)
        }

        fun provideDetailViewModel(id: UUID): DetailViewModel {
            return DetailViewModel(ScannedDocumentRepository, id)
        }
    }
}