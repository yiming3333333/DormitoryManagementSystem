import request from './request'

export const buildingApi = {
  list: () => request.get('/building/list'),
  add: (data) => request.post('/building/add', data),
  update: (data) => request.put('/building/update', data),
  delete: (id) => request.delete(`/building/delete/${id}`)
}

export const dormitoryApi = {
  list: () => request.get('/dormitory/listWithBuilding'),
  add: (data) => request.post('/dormitory/add', data),
  update: (data) => request.put('/dormitory/update', data),
  delete: (id) => request.delete(`/dormitory/delete/${id}`)
}

export const studentApi = {
  list: (params) => request.get('/student/list', { params }),
  add: (data) => request.post('/student/add', data),
  update: (data) => request.put('/student/update', data),
  delete: (id) => request.delete(`/student/delete/${id}`),
  checkIn: (data) => request.post('/student/checkIn', data),
  checkOut: (recordId) => request.post(`/student/checkOut/${recordId}`),
  checkInList: () => request.get('/student/checkInList')
}

export const repairApi = {
  list: () => request.get('/repair/list'),
  add: (data) => request.post('/repair/add', data),
  update: (data) => request.put('/repair/update', data),
  delete: (id) => request.delete(`/repair/delete/${id}`)
}

export const adminApi = {
  list: () => request.get('/admin/list'),
  add: (data) => request.post('/admin/add', data),
  update: (data) => request.put('/admin/update', data),
  delete: (id) => request.delete(`/admin/delete/${id}`),
  login: (data) => request.post('/admin/login', data)
}