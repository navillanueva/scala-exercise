package com.tw.energy.service

import com.tw.energy.domain.ElectricityReading
import com.tw.energy.domain.MeterReadings
import com.tw.energy.domain.StringTypes.SmartMeterId
import java.time.Instant

class MeterReadingService(private[service] var readingsByMeterId: Map[SmartMeterId, Seq[ElectricityReading]] = Map()) {
  def getReadings(smartMeterId: SmartMeterId): Option[Seq[ElectricityReading]] = {
    readingsByMeterId.get(smartMeterId)
  }

  // mapping the result of getReadings to filter the readings by time
  def getReadingsByTime(smartMeterId: SmartMeterId,
                        start: Instant,
                        end: Instant): Option[Seq[ElectricityReading]] = {
    getReadings(smartMeterId).map(_.filter(reading => reading.time.isAfter(start) && reading.time.isBefore(end)))
  }

  def storeReadings(meterReadings: MeterReadings): Unit = {
    val existingReadings = readingsByMeterId.getOrElse(meterReadings.smartMeterId, Seq())
    val updatedReadings = existingReadings ++ meterReadings.electricityReadings
    readingsByMeterId += (meterReadings.smartMeterId -> updatedReadings)
  }
}
